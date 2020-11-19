package com.rafatech.personcontactapi.integration_tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainerProvider;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

    @LocalServerPort
    protected int port;

    protected final String basePath = "/api/v1";

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    static {
        var container = new PostgreSQLContainerProvider().newInstance();
        container.start();

        System.setProperty("DATABASE_URL", container.getJdbcUrl());
        System.setProperty("DATABASE_USERNAME", container.getUsername());
        System.setProperty("DATABASE_PASSWORD", container.getPassword());
    }
}
