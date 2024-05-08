package com.example.bankapplication.converters;

import com.example.bankapplication.domain.Transaction;
import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.repositories.TransactionRepository;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter extends BaseConverter<Transaction, TransactionDto, TransactionRepository>{

    private final BankConverter bankConverter;
    private final ImporterConverter importerConverter;
    private final ExporterConverter exporterConverter;


    public TransactionConverter(BankConverter bankConverter, ImporterConverter importerConverter, ExporterConverter exporterConverter) {
        this.bankConverter = bankConverter;
        this.importerConverter = importerConverter;
        this.exporterConverter = exporterConverter;
    }

    @Override
    public TransactionDto createFromEntity(Transaction entity) {
        if (entity != null) {
            TransactionDto transactionDto = mapEntity(entity, TransactionDto.class);
            transactionDto.setBank(bankConverter.createFromEntity(entity.getBank()));
            transactionDto.setExporter(exporterConverter.createFromEntity(entity.getExporter()));
            transactionDto.setImporter(importerConverter.createFromEntity(entity.getImporter()));
            return transactionDto;
        }
        return null;
    }

    @Override
    public Transaction createFromDto(TransactionDto transactionDto) {
        if (transactionDto != null) {
            Transaction transaction = mapDto(transactionDto, Transaction.class);
            transaction.setBank(bankConverter.createFromDto(transactionDto.getBank()));
            transaction.setExporter(exporterConverter.createFromDto(transactionDto.getExporter()));
            transaction.setImporter(importerConverter.createFromDto(transactionDto.getImporter()));
            transaction.setAddress(transactionDto.getAddress());
            transaction.setComment(transactionDto.getComment());
            transaction.setEmail(transactionDto.getEmail());
            transaction.setUnit(transactionDto.getUnit());
            transaction.setPhone(transactionDto.getPhone());
            transaction.setGoodsType(transactionDto.getGoodsType());
            transaction.setBankAccountNumber(transactionDto.getBankAccountNumber());
            return transaction;
        }
        return null;
    }

}
