package com.example.bankapplication.repositories;

import com.example.bankapplication.domain.Importer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImporterRepository extends JpaRepository<Importer, Long>, BaseRepository<Importer> {
    @Override
    Importer findByUuid(String uuid);

}
