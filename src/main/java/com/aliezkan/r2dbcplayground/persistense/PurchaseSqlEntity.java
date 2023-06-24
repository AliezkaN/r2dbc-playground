package com.aliezkan.r2dbcplayground.persistense;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Table(name = "purchases")
public class PurchaseSqlEntity {

    @Id
    @Column("purchase_id")
    private Long purchaseId;
    @Column("client_id")
    private Long clientId;
    @Column("purchase_date")
    private LocalDate purchaseDate;
    @Column("product_name")
    private String productName;
    @Column("quantity")
    private Integer quantity;
    @Column("price")
    private BigDecimal price;

}
