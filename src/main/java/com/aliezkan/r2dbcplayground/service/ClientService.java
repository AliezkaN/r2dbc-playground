package com.aliezkan.r2dbcplayground.service;

import com.aliezkan.r2dbcplayground.domain.Client;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ClientService {
    Mono<List<Client>> getAllClients();
    Mono<Client> getClientById(Long clientId);
}
