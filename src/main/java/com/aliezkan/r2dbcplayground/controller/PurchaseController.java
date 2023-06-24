package com.aliezkan.r2dbcplayground.controller;

import com.aliezkan.r2dbcplayground.dto.ClientTotal;
import com.aliezkan.r2dbcplayground.dto.PurchaseDto;
import com.aliezkan.r2dbcplayground.service.PurchaseService;
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
@RequestMapping("aliezkaN/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final SelmaService selma;

    @GetMapping
    public Mono<List<PurchaseDto>> getAll() {
        return purchaseService.getAll()
                .map(entities -> entities.stream()
                        .map(selma::toDto)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{purchaseId}")
    public Mono<PurchaseDto> getPurchaseById(@PathVariable Long purchaseId) {
        return purchaseService.getPurchaseById(purchaseId).map(selma::toDto);
    }

    @GetMapping("/clients/{clientId}")
    private Mono<List<PurchaseDto>> getAllByClient(@PathVariable Long clientId) {
        return purchaseService.getAllByClientId(clientId)
                .map(entities -> entities.stream()
                        .map(selma::toDto)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/clients/{clientId}/total")
    private Mono<ClientTotal> getClientTotal(@PathVariable Long clientId){
     return purchaseService.getClientTotal(clientId).map(ClientTotal::new);
    }
}
