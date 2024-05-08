package com.example.bankapplication.services;

import com.example.bankapplication.converters.ImporterConverter;
import com.example.bankapplication.domain.Importer;
import com.example.bankapplication.dto.ImporterDto;
import com.example.bankapplication.repositories.ImporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImporterService extends BaseService<Importer, ImporterDto,
        ImporterRepository, ImporterConverter>{

    @Transactional
    public void populateImporters() {
        Importer importer1 = new Importer();
        importer1.setName("Imp1");
        repository.save(importer1);
        Importer importer2 = new Importer();
        importer2.setName("Imp2");
        repository.save(importer2);
        Importer importer3 = new Importer();
        importer3.setName("Imp3");
        repository.save(importer3);
    }


}
