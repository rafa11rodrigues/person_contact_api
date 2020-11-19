package com.rafatech.personcontactapi.port.rest.resource;

import com.rafatech.personcontactapi.integration_tests.PersonIntegrationTest;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class PersonResourceTest extends PersonIntegrationTest {

    private final String URL = basePath + "/people";


    @Test
    void filter() {
        System.out.println("\n\n" + URL);
        given()
                .param("name", "fO")
                .param("size", "2")
                .when().get(URL)
                .then()
                .statusCode(200)
                .body("content.size()", equalTo(1))
                .body("content[0].name", equalTo("Foo Person"))
                .body("totalElements", equalTo(1));
    }
}