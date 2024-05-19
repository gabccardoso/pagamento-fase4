package com.fiap.pagamento.application.gateways;

public interface PaymentGateway{
    String findPaymentStatus(Long orderId);

    String createPayment(Long orderId);
}
