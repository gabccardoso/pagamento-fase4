package com.fiap.pagamento.infrastructure.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class WebhookControllerTest {

    @Test
    void testReceberNotificacao_AuthorizationHeaderValido() {
        // Configurando os dados do payload e do token
        String payload = "Notificação recebida!";
        String token = "123";
        String authorizationHeader = "Bearer " + token;

        // Criando uma instância do WebhookController
        WebhookController webhookController = new WebhookController();

        // Chamando o método de receber notificação
        ResponseEntity<Void> responseEntity = webhookController.receberNotificacao(payload, authorizationHeader);

        // Verificando se a resposta está correta (deve ser OK)
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testReceberNotificacao_AuthorizationHeaderInvalido() {
        // Configurando os dados do payload e do token
        String payload = "Notificação recebida!";
        String token = "456"; // Token inválido
        String authorizationHeader = "Bearer " + token;

        // Criando uma instância do WebhookController
        WebhookController webhookController = new WebhookController();

        // Chamando o método de receber notificação
        ResponseEntity<Void> responseEntity = webhookController.receberNotificacao(payload, authorizationHeader);

        // Verificando se a resposta está correta (deve ser FORBIDDEN)
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void testReceberNotificacao_HeaderAuthorizationAusente() {
        // Configurando os dados do payload sem o token de autorização
        String payload = "Notificação recebida!";

        // Criando uma instância do WebhookController
        WebhookController webhookController = new WebhookController();

        // Chamando o método de receber notificação sem o header de autorização
        ResponseEntity<Void> responseEntity = webhookController.receberNotificacao(payload, null);

        // Verificando se a resposta está correta (deve ser FORBIDDEN)
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

}