package com.example.bankapplication.services;

import com.example.bankapplication.aspects.LogStartEndExecution;
import com.example.bankapplication.converters.ExporterConverter;
import com.example.bankapplication.domain.Exporter;
import com.example.bankapplication.dto.ExporterDto;
import com.example.bankapplication.repositories.ExporterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExporterService extends BaseService<Exporter, ExporterDto,
        ExporterRepository, ExporterConverter> {

    @LogStartEndExecution
    @Transactional
    public void populateExporters() {
        Exporter exporter1 = new Exporter();
        exporter1.setName("Exporter1");
        repository.save(exporter1);
        Exporter exporter2 = new Exporter();
        exporter2.setName("Exporter2");
        repository.save(exporter2);
        Exporter exporter3 = new Exporter();
        exporter3.setName("Exporter3");
        repository.save(exporter3);

    }

}
