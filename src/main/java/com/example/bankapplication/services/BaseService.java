package com.example.bankapplication.services;

import com.example.bankapplication.converters.BaseConverter;
import com.example.bankapplication.domain.BaseEntity;
import com.example.bankapplication.dto.BaseDto;
import com.example.bankapplication.repositories.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class BaseService<E extends BaseEntity, D extends BaseDto,
        R extends JpaRepository<E, Long> & BaseRepository<E>,
        C extends BaseConverter<E, D, R>>{


    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected R repository;

    @Autowired
    protected C converter;




    public List<D> getEntities() {
        return repository.findAll().stream()
                .map(converter::createFromEntity)
                .collect(Collectors.toList());
    }

    public E findByUuid(String uuid) {
        return repository.findByUuid(uuid);
    }



}
