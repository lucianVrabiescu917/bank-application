package com.example.bankapplication.converters;

import com.example.bankapplication.domain.Exporter;
import com.example.bankapplication.dto.ExporterDto;
import com.example.bankapplication.repositories.ExporterRepository;
import org.springframework.stereotype.Component;

@Component
public class ExporterConverter extends BaseConverter<Exporter, ExporterDto, ExporterRepository> {

    @Override
    public ExporterDto createFromEntity(Exporter entity) {
        if (entity != null) {
            ExporterDto exporterDto = mapEntity(entity, ExporterDto.class);
            return exporterDto;
        }
        return null;
    }

    @Override
    public Exporter createFromDto(ExporterDto dto) {
        if (dto != null) {
            Exporter exporter = mapDto(dto, Exporter.class);
            exporter.setName(exporter.getName());
            return exporter;
        }
        return null;
    }

}
