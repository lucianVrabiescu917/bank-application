package com.example.bankapplication.repositories;

public interface BaseRepository<E> {
    E findByUuid(String uuid);
}
