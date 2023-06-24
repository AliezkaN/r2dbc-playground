package com.aliezkan.r2dbcplayground.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PurchaseDto {
    private Long purchaseId;
    private Long clientId;
    private LocalDate purchaseDate;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
