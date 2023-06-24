package com.aliezkan.r2dbcplayground.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ClientTotal {
    private BigDecimal amount;
}
