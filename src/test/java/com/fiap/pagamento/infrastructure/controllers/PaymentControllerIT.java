package com.fiap.pagamento.infrastructure.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(LocalStackConfiguration.class)
public class PaymentControllerIT {

    @LocalServerPort
    private int port;
    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void fazPagamento_Sucesso() {
        given()
                .filter(new AllureRestAssured())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("orderId", 10L)
                .when()
                .post("/pagamento/fazPagamento")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
