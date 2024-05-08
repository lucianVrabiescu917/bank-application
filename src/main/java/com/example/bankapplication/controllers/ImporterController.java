package com.example.bankapplication.controllers;

import com.example.bankapplication.dto.ImporterDto;
import com.example.bankapplication.services.ImporterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/importer")
public class ImporterController {
    private final ImporterService importerService;

    public ImporterController(ImporterService importerService) {
        this.importerService = importerService;
    }

    @PostMapping(value = "/populate")
    public ResponseEntity<HttpStatus> populateImporters() {
        importerService.populateImporters();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/importers")
    public ResponseEntity<List<ImporterDto>> getImporters() {
        List<ImporterDto> importerDtos = importerService.getEntities();
        return new ResponseEntity<>(importerDtos, HttpStatus.OK);
    }
}
