package com.fiap.pagamento.infrastructure.controllers;

import com.fiap.pagamento.application.useCases.PaymentInteractor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/pagamento")
public class PaymentController {

    private final PaymentInteractor paymentInteractor;

    public PaymentController(PaymentInteractor paymentInteractor) {
        this.paymentInteractor = paymentInteractor;
    }


    @GetMapping(value = "buscarStatusPagamento/{pedidoId}")
    public ResponseEntity<String> buscarStatusPagamento(@PathVariable Long pedidoId){
        return ResponseEntity.ok(paymentInteractor.findPaymentStatus(pedidoId));
    }

    @PostMapping(value = "fazPagamento")
    public ResponseEntity<String> fazPagamento(@RequestParam Long orderId){
        String mensagem = paymentInteractor.createPayment(orderId);
        enviarNotificacao(mensagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentInteractor.createPayment(orderId));
    }

    public void enviarNotificacao(String mensagem) {
        String payload = "{\"Seu pagamento foi\": \"" + mensagem.toLowerCase() + "\"}";
        WebhookController webhookController = new WebhookController();
        webhookController.receberNotificacao(payload, "Bearer 123");
    }

}
