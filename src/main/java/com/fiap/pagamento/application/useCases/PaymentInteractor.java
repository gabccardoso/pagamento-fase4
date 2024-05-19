package com.fiap.pagamento.application.useCases;


import com.amazonaws.services.dynamodbv2.xspec.S;
import com.fiap.pagamento.application.gateways.PaymentGateway;

public class PaymentInteractor {

    private final PaymentGateway paymentGateway;

    public PaymentInteractor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String findPaymentStatus(Long orderId){
        return paymentGateway.findPaymentStatus(orderId);
    }

    public String createPayment(Long orderId){
        return paymentGateway.createPayment(orderId);
    }
}
