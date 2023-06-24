package com.aliezkan.r2dbcplayground.dao.impl;

import com.aliezkan.r2dbcplayground.dao.ClientDAO;
import com.aliezkan.r2dbcplayground.domain.Client;
import com.aliezkan.r2dbcplayground.persistense.ClientSqlEntity;
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
public class ClientDAOImpl implements ClientDAO {
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final SelmaService selma;

    @Override
    public Mono<List<Client>> getAll() {
        return r2dbcEntityTemplate
                .select(ClientSqlEntity.class).all()
                .collectList()
                .map(entities -> entities.stream()
                        .map(selma::toDomain)
                        .collect(Collectors.toList()))
                .cache();
    }

    public Mono<Optional<Client>> getById(Long clientId) {
        return r2dbcEntityTemplate.selectOne(
                        Query.query(Criteria.where("clientId").is(clientId)),
                        ClientSqlEntity.class)
                .map(selma::toDomain)
                .map(Optional::of)
                .switchIfEmpty(Mono.just(Optional.empty()))
                .cache();
    }
}
