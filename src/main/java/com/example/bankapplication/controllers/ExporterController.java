package com.example.bankapplication.controllers;

import com.example.bankapplication.dto.ExporterDto;
import com.example.bankapplication.services.ExporterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exporter")
public class ExporterController {
    private final ExporterService exporterService;

    public ExporterController(ExporterService exporterService) {
        this.exporterService = exporterService;
    }

    @PostMapping(value = "/populate")
    public ResponseEntity<HttpStatus> populateImporters() {
        exporterService.populateExporters();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/exporters")
    public ResponseEntity<List<ExporterDto>> getImporters() {
        List<ExporterDto> exporterDtos = exporterService.getEntities();
        return new ResponseEntity<>(exporterDtos, HttpStatus.OK);
    }


}
