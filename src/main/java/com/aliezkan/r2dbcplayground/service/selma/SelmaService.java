package com.aliezkan.r2dbcplayground.service.selma;

import com.aliezkan.r2dbcplayground.domain.Client;
import com.aliezkan.r2dbcplayground.domain.Purchase;
import com.aliezkan.r2dbcplayground.dto.ClientDto;
import com.aliezkan.r2dbcplayground.persistense.ClientSqlEntity;
import com.aliezkan.r2dbcplayground.persistense.PurchaseSqlEntity;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

@Mapper(withIoC = IoC.SPRING)
public interface SelmaService {
    Client toDomain(ClientSqlEntity entity);
    Purchase toDomain(PurchaseSqlEntity entity);

    ClientDto toDto(Client domain);
}
