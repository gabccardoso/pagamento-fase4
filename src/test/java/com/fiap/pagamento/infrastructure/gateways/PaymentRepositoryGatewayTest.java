package com.fiap.pagamento.infrastructure.gateways;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fiap.pagamento.infrastructure.controllers.enums.StatusPayment;
import com.fiap.pagamento.infrastructure.gateways.gerenciadores.GerenciadorRabbit;
import com.fiap.pagamento.infrastructure.persistence.PaymentEntity;
import com.fiap.pagamento.infrastructure.persistence.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentRepositoryGatewayTest {

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    GerenciadorRabbit gerenciadorRabbit;
    @InjectMocks
    private PaymentRepositoryGateway paymentRepositoryGateway;
    private DynamoDBMapper dynamoDBMapperMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentRepositoryGateway = new PaymentRepositoryGateway(paymentRepository, gerenciadorRabbit);
    }

    @Test
    public void testFindPaymentStatus() {
        // Configuração do mock para retornar um resultado simulado para a query
        Optional<PaymentEntity> simulatedPaymentEntity = Optional.of(new PaymentEntity(1L, StatusPayment.APROVADO.name()));
        when(paymentRepository.findById(anyLong())).thenReturn(simulatedPaymentEntity);

        // Chama o método a ser testado
        String paymentStatus = paymentRepositoryGateway.findPaymentStatus(1L);

        // Verifica se o status do pagamento retornado é o esperado
        assertEquals("APROVADO", paymentStatus);
    }

    @Test
    public void testCreatePayment() {

        String status = paymentRepositoryGateway.createPayment(123L);

        // Verificar se a operação foi realizada
        verify(paymentRepository).save(any(PaymentEntity.class));

        // Verificar se o status retornado é o esperado
        assertEquals("APROVADO", status);
    }
}
