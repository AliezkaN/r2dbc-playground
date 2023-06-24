package com.aliezkan.r2dbcplayground.persistense;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "client")
public class ClientSqlEntity {

    @Id
    @Column("client_id")
    private Long clientId;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @Column("email")
    private String email;
    @Column("phone_number")
    private String phoneNumber;
    @Column("address")
    private String address;
}
