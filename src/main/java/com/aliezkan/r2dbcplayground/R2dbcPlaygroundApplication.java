package com.aliezkan.r2dbcplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class R2dbcPlaygroundApplication {
    public static void main(String[] args) {
        SpringApplication.run(R2dbcPlaygroundApplication.class, args);
    }
}
