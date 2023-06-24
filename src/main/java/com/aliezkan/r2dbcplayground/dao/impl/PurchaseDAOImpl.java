package com.aliezkan.r2dbcplayground.dao.impl;

import com.aliezkan.r2dbcplayground.dao.PurchaseDAO;
import com.aliezkan.r2dbcplayground.domain.Purchase;
import com.aliezkan.r2dbcplayground.persistense.PurchaseSqlEntity;
import com.aliezkan.r2dbcplayground.service.selma.SelmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PurchaseDAOImpl implements PurchaseDAO {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final SelmaService selma;

    @Override
    public Mono<List<Purchase>> findAll() {
        return r2dbcEntityTemplate.select(PurchaseSqlEntity.class).all()
                .collectList()
                .map(entities -> entities.stream()
                        .map(selma::toDomain)
                        .collect(Collectors.toList()))
                .cache();
    }

    @Override
    public Mono<List<Purchase>> findAllByClientId(Long clientId) {
        return r2dbcEntityTemplate.select(
                        Query.query(Criteria.where("clientId").is(clientId)),
                        PurchaseSqlEntity.class)
                .collectList()
                .map(entities -> entities.stream()
                        .map(selma::toDomain)
                        .collect(Collectors.toList()))
                .cache();
    }

    @Override
    public Mono<Optional<Purchase>> findByPurchaseId(Long purchaseId) {
        return r2dbcEntityTemplate.selectOne(
                        Query.query(Criteria.where("purchaseId").is(purchaseId)),
                        PurchaseSqlEntity.class)
                .map(selma::toDomain)
                .map(Optional::of)
                .switchIfEmpty(Mono.just(Optional.empty()))
                .cache();
    }
}
