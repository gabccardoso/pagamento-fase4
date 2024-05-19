package com.fiap.pagamento.infrastructure.gateways;

import com.fiap.pagamento.application.gateways.PaymentGateway;
import com.fiap.pagamento.infrastructure.gateways.gerenciadores.GerenciadorRabbit;
import com.fiap.pagamento.infrastructure.persistence.PaymentEntity;
import com.fiap.pagamento.infrastructure.persistence.PaymentRepository;

import java.util.Optional;


public class PaymentRepositoryGateway implements PaymentGateway {

    private final PaymentRepository paymentRepository;
    private final GerenciadorRabbit gerenciadorRabbit;

    public PaymentRepositoryGateway(PaymentRepository paymentRepository, GerenciadorRabbit gerenciadorRabbit) {
        this.paymentRepository = paymentRepository;
        this.gerenciadorRabbit = gerenciadorRabbit;
    }

    @Override
    public String findPaymentStatus(Long orderId) {
        Optional<PaymentEntity> results = paymentRepository.findById(orderId);
        if (results.isEmpty()) {
            return null; // Nenhum item encontrado com a chave de partição fornecida
        } else {
            return results.get().getPagamento(); // Retorna o primeiro item encontrado
        }
    }

    @Override
    public String createPayment(Long orderId) {
        PaymentEntity paymentEntity = new PaymentEntity(orderId);
        paymentEntity.setPagamento("APROVADO");
        paymentRepository.save(paymentEntity);
        gerenciadorRabbit.ColocaPedidoNaFilaDePagos(orderId);
        return paymentEntity.getPagamento();
    }
}
