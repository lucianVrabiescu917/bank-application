package com.example.bankapplication.converters;

import com.example.bankapplication.domain.Bank;
import com.example.bankapplication.dto.BankDto;
import com.example.bankapplication.repositories.BankRepository;
import org.springframework.stereotype.Component;

@Component
public class BankConverter extends BaseConverter<Bank, BankDto, BankRepository> {

    private final BankRepository bankRepository;

    public BankConverter(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public BankDto createFromEntity(Bank entity) {
        if (entity != null) {
            BankDto bankDto = mapEntity(entity, BankDto.class);
//            bankDto.setName(entity.getName());
            return bankDto;
        }
        return null;
    }

    @Override
    public Bank createFromDto(BankDto dto) {
        if (dto != null) {
            Bank bank = mapDto(dto, Bank.class);
            bank.setName(bank.getName());
            return bank;
        }
        return null;
    }
}
