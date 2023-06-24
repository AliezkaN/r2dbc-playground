package com.aliezkan.r2dbcplayground.dao;

import com.aliezkan.r2dbcplayground.domain.Client;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    Mono<List<Client>> getAll();
    Mono<Optional<Client>> getById(Long clientId);
}
