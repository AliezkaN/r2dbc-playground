package com.aliezkan.r2dbcplayground.domain;

import lombok.Data;

@Data
public class Client {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
