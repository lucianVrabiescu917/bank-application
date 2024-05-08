package com.example.bankapplication.controllers;

import com.example.bankapplication.dto.TransactionDto;
import com.example.bankapplication.services.TransactionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto transactionDtoCreated = transactionService.createTransactionAndConvert(transactionDto);
        return new ResponseEntity<>(transactionDtoCreated, HttpStatus.OK);
    }

    @GetMapping(value = "/bank/{bankUuid}")
    public ResponseEntity<List<TransactionDto>> getTransactionsForBank(@PathVariable("bankUuid") String bankUuid) {
        List<TransactionDto> transactionDtos = transactionService.getTransactionsForBank(bankUuid);
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/importer/{importerUuid}")
    public ResponseEntity<List<TransactionDto>> getTransactionsForImporter(@PathVariable("importerUuid") String importerUuid) {
        List<TransactionDto> transactionDtos = transactionService.getTransactionsForImporter(importerUuid);
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/exporter/{exporterUuid}")
    public ResponseEntity<List<TransactionDto>> getTransactionsForExporter(@PathVariable("exporterUuid") String exporterUuid) {
        List<TransactionDto> transactionDtos = transactionService.getTransactionsForExporter(exporterUuid);
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }


    @PutMapping("/accept-bank")
    HttpEntity<TransactionDto> acceptBankTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto transactionDtoUpdated = transactionService.acceptBankTransaction(transactionDto);
        return new ResponseEntity<>(transactionDtoUpdated, HttpStatus.OK);
    }

    @PutMapping("/declined-bank/{uuid}")
    HttpEntity<TransactionDto> declineBankTransaction(@PathVariable("uuid") String uuid) {
        TransactionDto transactionDto = transactionService.declineBankTransaction(uuid);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    @PutMapping("/accept-exporter")
    HttpEntity<TransactionDto> acceptExporterTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto transactionDtoUpdated = transactionService.acceptExporterTransaction(transactionDto);
        return new ResponseEntity<>(transactionDtoUpdated, HttpStatus.OK);
    }

    @PutMapping("/declined-exporter/{uuid}")
    HttpEntity<TransactionDto> declineExporterTransaction(@PathVariable("uuid") String uuid) {
        TransactionDto transactionDto = transactionService.declineExporterTransaction(uuid);
        return new ResponseEntity<>(transactionDto, HttpStatus.OK);
    }

    @PutMapping("/force-scheduled")
    HttpEntity<TransactionDto> forceScheduled() {
        transactionService.executeVATCalculation();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
