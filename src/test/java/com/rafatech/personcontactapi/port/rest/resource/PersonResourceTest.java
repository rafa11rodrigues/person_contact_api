package com.rafatech.personcontactapi.port.rest.resource;

import com.rafatech.personcontactapi.domain.person.repository.PersonRepository;
import com.rafatech.personcontactapi.integration_tests.PersonIntegrationTest;
import com.rafatech.personcontactapi.port.rest.response.PersonResponse;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.head;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class PersonResourceTest extends PersonIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    private final String URL = basePath + "/people";

    private Map<String, String> contentRelatedHeaders = Map.of(
            "Content-Type", "application/json",
            "Character-Encoding", "UTF-8"
    );


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

    @Test
    void create() {
        var body = "{" +
                "\"name\": \"Someone\"," +
                "\"cpf\": \"36065429090\"," +
                "\"birthDate\": \"2001-01-04\"," +
                "\"contacts\": [{" +
                "   \"name\": \"A contact\"," +
                "   \"phone\": \"41987654321\"," +
                "   \"email\": \"contact@gmail.com\"" +
                "}]" +
                "}";

        var id = given()
                .headers(contentRelatedHeaders)
                .body(body)
                .when().post(URL)
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Someone"))
                .body("contacts.size()", equalTo(1))
                .extract().jsonPath().getString("id");

        personRepository.deleteById(UUID.fromString(id));
    }

    @Test
    void create_invalidBody_throwException() {
        var body = "{}";

        given()
                .headers(contentRelatedHeaders)
                .body(body)
                .when().post(URL)
                .then()
                .statusCode(400)
                .body("messages", Matchers.containsInAnyOrder("Person name is required", "Person birth date is required", "Person CPF is required"));
    }
}