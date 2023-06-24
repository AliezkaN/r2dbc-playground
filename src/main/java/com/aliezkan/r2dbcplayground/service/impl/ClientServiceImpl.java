package com.aliezkan.r2dbcplayground.service.impl;

import com.aliezkan.r2dbcplayground.dao.ClientDAO;
import com.aliezkan.r2dbcplayground.domain.Client;
import com.aliezkan.r2dbcplayground.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO;
    public Mono<List<Client>> getAllClients() {
        return clientDAO.findAll().cache();
    }

    public Mono<Client> getClientById(Long clientId){
        return clientDAO.findById(clientId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .cache();
    }
}
