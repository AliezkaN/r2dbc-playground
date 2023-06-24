package com.aliezkan.r2dbcplayground.dto;

import lombok.Data;

@Data
public class ClientDto {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
