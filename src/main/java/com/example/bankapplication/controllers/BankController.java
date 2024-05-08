package com.example.bankapplication.controllers;

import com.example.bankapplication.dto.BankDto;
import com.example.bankapplication.services.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping(value = "/populate")
    public ResponseEntity<HttpStatus> populateBanks() {
        bankService.populateBanks();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/banks")
    public ResponseEntity<List<BankDto>> getBanks() {
        List<BankDto> bankDto = bankService.getEntities();
        return new ResponseEntity<>(bankDto, HttpStatus.OK);
    }

}

