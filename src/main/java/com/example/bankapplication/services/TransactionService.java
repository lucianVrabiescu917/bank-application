package com.example.bankapplication.services;

import com.example.bankapplication.aspects.LogStartEndExecution;
import com.example.bankapplication.converters.TransactionConverter;
import com.example.bankapplication.domain.Bank;
import com.example.bankapplication.domain.Exporter;
import com.example.bankapplication.domain.Importer;
import com.example.bankapplication.domain.Transaction;
import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.enums.Status;
import com.example.bankapplication.exceptions.IncorrectStatusException;
import com.example.bankapplication.exceptions.NotFoundException;
import com.example.bankapplication.repositories.TransactionRepository;
import com.example.bankapplication.utils.StatusUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService extends BaseService<Transaction, TransactionDto, TransactionRepository, TransactionConverter>{

    private final BankService bankService;

    private final ImporterService importerService;

    private final ExporterService exporterService;

    private final TransactionWebSocketHandler transactionWebSocketHandler;

    public TransactionService(BankService bankService, ImporterService importerService, ExporterService exporterService, TransactionWebSocketHandler transactionWebSocketHandler) {
        this.bankService = bankService;
        this.importerService = importerService;
        this.exporterService = exporterService;
        this.transactionWebSocketHandler = transactionWebSocketHandler;
    }

    @Transactional
    @LogStartEndExecution
    public TransactionDto createTransactionAndConvert(TransactionDto transactionDto) {
        TransactionDto transactionDtoCreated =
                converter.createFromEntity(createTransaction(transactionDto, Status.PENDING));
        StatusUtils.STATUS_ROLES_MAP.get(Status.PENDING).forEach(
                role -> transactionWebSocketHandler.sendTransaction(transactionDtoCreated, role));
        return transactionDtoCreated;
    }

    private Transaction createTransaction(TransactionDto transactionDto, Status status) {
        if (transactionDto != null) {
            Transaction transaction = converter.createFromDto(transactionDto);
            if (checkIfStatusChangeLogicIsValid(transaction.getStatus(), status)) {
                transaction.setStatus(status);
                return repository.save(transaction);
            } else {
                throw new IncorrectStatusException("Incorrect status change!");
            }

        } else {
            throw new NotFoundException("Received TransactionDto is null.");
        }
    }

    private boolean checkIfStatusChangeLogicIsValid(Status originalStatus, Status newStatus) {
        return (originalStatus == null && newStatus == Status.PENDING)
            || (originalStatus == Status.PENDING && (newStatus == Status.ACCEPTED_BANK || newStatus == Status.DECLINED_BANK))
            || (originalStatus == Status.ACCEPTED_BANK && (newStatus == Status.ACCEPTED || newStatus == Status.DECLINED))
            || (originalStatus == Status.DECLINED_BANK && newStatus == Status.PENDING)
            || (originalStatus == Status.DECLINED && (newStatus == Status.ACCEPTED_BANK || newStatus == Status.DECLINED_BANK));
    }

    @LogStartEndExecution
    @Transactional(readOnly = true)
    public List<TransactionDto> getTransactionsForBank(String bankUuid) {
        if (bankUuid != null) {
            Bank bank = this.bankService.findByUuid(bankUuid);
            if (bank != null) {
                List<Transaction> transactions = repository.findAllByBankIdAndStatusInOrderByCreatedDateDesc(bank.getId(),
                        StatusUtils.BANK_STATUSES);
                return transactions.stream().map(converter::createFromEntity).toList();
            } else {
                throw new NotFoundException("Bank not found!");
            }
        } else {
            throw new NotFoundException("Received bank uuid is null.");
        }
    }

    @LogStartEndExecution
    @Transactional(readOnly = true)
    public List<TransactionDto> getTransactionsForImporter(String importerUuid) {
        if (importerUuid != null) {
            Importer importer = this.importerService.findByUuid(importerUuid);
            if (importer != null) {
                List<Transaction> transactions = repository.findAllByImporterIdAndStatusInOrderByCreatedDateDesc(importer.getId(),
                        StatusUtils.IMPORTER_STATUSES);
                return transactions.stream().map(converter::createFromEntity).toList();
            } else {
                throw new NotFoundException("Importer not found!");
            }
        } else {
            throw new NotFoundException("Received importer uuid is null.");
        }
    }

    @LogStartEndExecution
    @Transactional(readOnly = true)
    public List<TransactionDto> getTransactionsForExporter(String exporterUuid) {
        if (exporterUuid != null) {
            Exporter exporter = this.exporterService.findByUuid(exporterUuid);
            if (exporter != null) {
                List<Transaction> transactions = repository.findAllByExporterIdAndStatusInOrderByCreatedDateDesc(exporter.getId(),
                        StatusUtils.EXPORTER_STATUSES);
                return transactions.stream().map(converter::createFromEntity).toList();
            } else {
                throw new NotFoundException("Exporter not found!");
            }
        } else {
            throw new NotFoundException("Received exporter uuid is null.");
        }
    }

    @LogStartEndExecution
    @Transactional
    public TransactionDto acceptBankTransaction(TransactionDto transactionDto) {
        return modifyStatusAndUpdateTransaction(transactionDto, Status.ACCEPTED_BANK);
    }

    @LogStartEndExecution
    @Transactional
    public TransactionDto declineBankTransaction(String uuid) {
        return modifyStatus(uuid, Status.DECLINED_BANK);
    }

    @LogStartEndExecution
    @Transactional
    public TransactionDto acceptExporterTransaction(TransactionDto transactionDto) {
        return modifyStatusAndUpdateTransaction(transactionDto, Status.ACCEPTED);
    }

    @LogStartEndExecution
    @Transactional
    public TransactionDto declineExporterTransaction(String uuid) {
        return modifyStatus(uuid, Status.DECLINED);
    }

    public TransactionDto modifyStatusAndUpdateTransaction(TransactionDto transactionDto, Status status) {
        if (transactionDto != null) {
            if (repository.existsByUuid(transactionDto.getUuid())) {
                Transaction transaction = createTransaction(transactionDto, status);
                TransactionDto transactionDtoUpdated = converter.createFromEntity(transaction);
                StatusUtils.STATUS_ROLES_MAP.get(status).forEach(
                        role -> transactionWebSocketHandler.sendTransaction(transactionDtoUpdated, role));
                return transactionDtoUpdated;
            } else {
                throw new IncorrectStatusException("Incorrect status change!");
            }
        } else {
            throw new NotFoundException("Received TransactionDto is null.");
        }
    }

    public TransactionDto modifyStatus(String uuid, Status status) {
        Transaction transaction = repository.findByUuid(uuid);
        if (transaction != null) {
            if (checkIfStatusChangeLogicIsValid(transaction.getStatus(), status)) {
                transaction.setStatus(status);
                transaction = repository.save(transaction);
                TransactionDto transactionDto = converter.createFromEntity(transaction);
                StatusUtils.STATUS_ROLES_MAP.get(status).forEach(
                        role -> transactionWebSocketHandler.sendTransaction(transactionDto, role));
                return transactionDto;
            } else {
                throw new NotFoundException("Transaction not found to update.");
            }

        } else {
            throw new NotFoundException("Transaction not found!");
        }
    }

    @LogStartEndExecution
    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    public void executeVATCalculation() {
        this.repository.updateVATNetProfit();
    }
}
