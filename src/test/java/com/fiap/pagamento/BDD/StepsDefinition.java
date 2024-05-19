package com.fiap.pagamento.BDD;


import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class StepsDefinition {

    private Response response;
    private final String ENDIPOINT_API_ORDER = "http://localhost:5533/pagamento";

    @Quando("registrar um novo pagamento")
    public void registrar_um_novo_pagamento() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("orderId", 10L)
                .when()
                .post( ENDIPOINT_API_ORDER+"/fazPagamento");
    }
    @Entao("o pagamento é registrado com sucesso")
    public void o_pagamento_é_registrado_com_sucesso() {
        response.then().statusCode(201);
    }

    @Dado("que já existe um pagamento")
    public void que_já_existe_um_pagamento() {
        registrar_um_novo_pagamento();
    }
    @Quando("buscar esse pagamento")
    public void buscar_esse_pagamento() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get( ENDIPOINT_API_ORDER+"/buscarStatusPagamento/10");
    }
    @Entao("o pagamento retorna com sucesso")
    public void o_pagamento_retorna_com_sucesso() {
        response.then().statusCode(200);
    }


}
