package com.example.bankapplication.services;

import com.example.bankapplication.converters.BankConverter;
import com.example.bankapplication.domain.Bank;
import com.example.bankapplication.dto.BankDto;
import com.example.bankapplication.repositories.BankRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService extends BaseService<Bank, BankDto,
        BankRepository, BankConverter>{

    @Transactional
    public void populateBanks() {
        Bank bank1 = new Bank();
        bank1.setName("Bank1");
        Bank bank2 = new Bank();
        bank2.setName("Bank2");
        Bank bank3 = new Bank();
        bank3.setName("Bank3");
        repository.save(bank1);
        repository.save(bank2);
        repository.save(bank3);

    }

}
