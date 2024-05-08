package com.example.bankapplication.repositories;

import com.example.bankapplication.domain.Transaction;
import com.example.bankapplication.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, BaseRepository<Transaction>{
    @Override
    Transaction findByUuid(String uuid);

    boolean existsByUuid(String uuid);

    List<Transaction> findAllByBankIdAndStatusInOrderByCreatedDateDesc(Long bankId, List<Status> status);

    List<Transaction> findAllByImporterIdAndStatusInOrderByCreatedDateDesc(Long importerId, List<Status> status);

    List<Transaction> findAllByExporterIdAndStatusInOrderByCreatedDateDesc(Long exporterId, List<Status> status);

    @Modifying
    @Query("UPDATE Transaction t SET t.netPrice = t.netPrice * 1.2, t.vatComputed = true" +
            " WHERE t.status = 'ACCEPTED' and (t.vatComputed = false or t.vatComputed is null)")
    void updateVATNetProfit();
}
