package com.aliezkan.r2dbcplayground.service;

import com.aliezkan.r2dbcplayground.domain.Purchase;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseService {
    Mono<List<Purchase>> getAll();
    Mono<List<Purchase>> getAllByClientId(Long clientId);
    Mono<Purchase> getPurchaseById(Long purchaseId);
    Mono<BigDecimal> getClientTotal(Long clientId);
}
