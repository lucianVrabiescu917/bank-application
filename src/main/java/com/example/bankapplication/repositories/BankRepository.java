package com.example.bankapplication.repositories;

import com.example.bankapplication.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>, BaseRepository<Bank> {
    @Override
    Bank findByUuid(String uuid);
}
