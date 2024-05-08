package com.example.bankapplication.repositories;

import com.example.bankapplication.domain.Exporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExporterRepository extends JpaRepository<Exporter, Long>, BaseRepository<Exporter> {
    @Override
    Exporter findByUuid(String uuid);
}
