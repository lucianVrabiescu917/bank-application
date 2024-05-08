package com.example.bankapplication.converters;

import com.example.bankapplication.domain.Importer;
import com.example.bankapplication.dto.ImporterDto;
import com.example.bankapplication.repositories.ImporterRepository;
import org.springframework.stereotype.Component;

@Component
public class ImporterConverter extends BaseConverter<Importer, ImporterDto, ImporterRepository> {

    @Override
    public ImporterDto createFromEntity(Importer entity) {
        if (entity != null) {
            ImporterDto importerDto = mapEntity(entity, ImporterDto.class);
            return importerDto;
        }
        return null;
    }

    @Override
    public Importer createFromDto(ImporterDto dto) {
        if (dto != null) {
            Importer importer = mapDto(dto, Importer.class);
            importer.setName(importer.getName());
            return importer;
        }
        return null;
    }
}
