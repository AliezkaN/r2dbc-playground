package com.aliezkan.r2dbcplayground.dao;

import com.aliezkan.r2dbcplayground.domain.Purchase;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface PurchaseDAO {
    Mono<List<Purchase>> findAll();
    Mono<List<Purchase>> findAllByClientId(Long clientId);
    Mono<Optional<Purchase>> findByPurchaseId(Long purchaseId);
}
