package com.aliezkan.r2dbcplayground.service.impl;

import com.aliezkan.r2dbcplayground.dao.PurchaseDAO;
import com.aliezkan.r2dbcplayground.domain.Purchase;
import com.aliezkan.r2dbcplayground.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseDAO purchaseDAO;

    @Override
    public Mono<List<Purchase>> getAll() {
        return purchaseDAO.findAll().cache();
    }

    @Override
    public Mono<List<Purchase>> getAllByClientId(Long clientId) {
        return purchaseDAO.findAllByClientId(clientId).cache();
    }

    @Override
    public Mono<Purchase> getPurchaseById(Long purchaseId) {
        return purchaseDAO.findByPurchaseId(purchaseId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .cache();
    }

    @Override
    public Mono<BigDecimal> getClientTotal(Long clientId) {
        return purchaseDAO.findAllByClientId(clientId)
                .map(entities -> entities.stream()
                        .map(Purchase::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .cache();
    }
}
