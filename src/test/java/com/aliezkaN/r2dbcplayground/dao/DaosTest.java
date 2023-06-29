package com.aliezkan.r2dbcplayground.dao;

import com.aliezkan.r2dbcplayground.configuration.DatabaseInitializerConfiguration;
import com.aliezkan.r2dbcplayground.dao.impl.ClientDAOImpl;
import com.aliezkan.r2dbcplayground.service.selma.SelmaService;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.junit.jupiter.api.Test;
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

@Testcontainers
@ExtendWith({SpringExtension.class})
@Import({ClientDAOImpl.class})
@ComponentScan(basePackageClasses = {SelmaService.class})
public class DaosTest {
    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres");

    @Autowired ClientDAOImpl clientDAO;

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

    @Test
    void test(){
        System.out.println(clientDAO.findAll().block());
    }
}
