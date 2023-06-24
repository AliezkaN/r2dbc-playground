package com.aliezkan.r2dbcplayground.controller;

import com.aliezkan.r2dbcplayground.dto.ClientDto;
import com.aliezkan.r2dbcplayground.service.ClientPurchaseService;
import com.aliezkan.r2dbcplayground.service.selma.SelmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("aliezkaN/api/clients")
public class ClientController {

    private final ClientPurchaseService clientPurchaseService;
    private final SelmaService selma;

    @GetMapping
    Mono<List<ClientDto>> getAll() {
        return clientPurchaseService.getAllClients()
                .map(entities -> entities.stream()
                        .map(selma::toDto)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{clientId}")
    Mono<ClientDto> getClientById(@PathVariable Long clientId){
        return clientPurchaseService.getClientById(clientId).map(selma::toDto);
    }
}
