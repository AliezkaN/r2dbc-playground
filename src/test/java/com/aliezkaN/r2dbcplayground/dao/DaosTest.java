package com.aliezkan.r2dbcplayground.dao;

import com.aliezkan.r2dbcplayground.configuration.DatabaseInitializerConfiguration;
import com.aliezkan.r2dbcplayground.dao.impl.ClientDAOImpl;
import com.aliezkan.r2dbcplayground.domain.Client;
import com.aliezkan.r2dbcplayground.service.selma.SelmaService;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.PostgreSQLR2DBCDatabaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.stream.Stream;

import static com.aliezkan.r2dbcplayground.utils.TestConstants.*;

@Testcontainers
@ExtendWith({SpringExtension.class})
@Import({ClientDAOImpl.class})
@ComponentScan(basePackageClasses = {SelmaService.class})
public class DaosTest {

    @TestConfiguration
    @Import(DatabaseInitializerConfiguration.class)
    static class TestConfig {
        @Bean
        public ConnectionFactory connectionFactory() {
            ConnectionFactoryOptions connectionFactoryOptions = PostgreSQLR2DBCDatabaseContainer.getOptions(postgres);
            return ConnectionFactories.get(connectionFactoryOptions);
        }

        @Bean
        R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
            return new R2dbcEntityTemplate(connectionFactory);
        }
    }

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres");

    @Autowired
    private ClientDAOImpl clientDAO;

    @TestFactory
    Stream<DynamicNode> tests() {
        return Stream.of(
                DynamicTest.dynamicTest("find all clients test", () ->
                        Assertions.assertEquals(EXPECTED_SIZE_OF_CLIENTS, clientDAO.findAll().block().size())),
                DynamicTest.dynamicTest("find client by id test with client", () ->
                        Assertions.assertEquals(
                                Optional.of(new Client()
                                        .setClientId(CLIENT_ID)
                                        .setFirstName(CLIENT_FIRST_NAME)
                                        .setLastName(CLIENT_LAST_NAME)
                                        .setEmail(CLIENT_EMAIL)
                                        .setPhoneNumber(CLIENT_PHONE)
                                        .setAddress(CLIENT_ADDRESS)),
                                clientDAO.findById(CLIENT_ID).block())),
                DynamicTest.dynamicTest("find client by id test with empty response", () ->
                        Assertions.assertEquals(Optional.empty(), clientDAO.findById(NOT_EXISTING_CLIENT_ID).block()))
        );
    }
}
