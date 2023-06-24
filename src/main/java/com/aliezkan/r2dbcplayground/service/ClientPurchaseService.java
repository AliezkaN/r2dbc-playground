package com.aliezkan.r2dbcplayground.service;

import com.aliezkan.r2dbcplayground.dao.ClientDAO;
import com.aliezkan.r2dbcplayground.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientPurchaseService {

    private final ClientDAO clientDAO;
    public Mono<List<Client>> getAllClients() {
        return clientDAO.getAll().cache();
    }

    public Mono<Client> getClientById(Long clientId){
        return clientDAO.getById(clientId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .cache();
    }
}
