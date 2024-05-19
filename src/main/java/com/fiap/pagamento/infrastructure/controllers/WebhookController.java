package com.fiap.pagamento.infrastructure.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {

    @PostMapping("webhooks/pagamentos")
    public ResponseEntity<Void> receberNotificacao(@RequestBody String payload,
                                                   @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false)
                                   String authorizationHeader){

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String token = authorizationHeader.substring(7);
        if (!token.equals("123")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // O token é válido, processar a notificação
        System.out.println(payload);
        return ResponseEntity.ok().build();

    }
}
