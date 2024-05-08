package com.example.bankapplication.converters;

import com.example.bankapplication.domain.BaseEntity;
import com.example.bankapplication.dto.BaseDto;
import com.example.bankapplication.repositories.BaseRepository;
import com.example.bankapplication.utils.UuidUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public abstract class BaseConverter<E extends BaseEntity, D extends BaseDto, R extends BaseRepository<E>> {

    @Autowired
    private R repository;

    @Autowired
    protected ModelMapper modelMapper;

    public abstract E createFromDto(D dto);

    public abstract D createFromEntity(E entity);

    public E mapDto(D dto, Class destination) {
        if (dto == null) {
            return null;
        }

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        E entity = (E) modelMapper.map(dto, destination);

        E oldEntity = (E) repository.findByUuid(dto.getUuid());

        if (UuidUtils.isValid(dto.getUuid())) {
            if (oldEntity != null) {
                entity.setUuid(oldEntity.getUuid());
                entity.setId(oldEntity.getId());
                entity.setVersion(oldEntity.getVersion());
                entity.setCreatedDate(oldEntity.getCreatedDate());
            }
        } else {
            entity.setUuid(UuidUtils.generateUuid());
        }


        return entity;
    }

    public D mapEntity(E entity, Class destination) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return (D)modelMapper.map(entity, destination);
    }
}
