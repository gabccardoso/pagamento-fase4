package com.fiap.pagamento.infrastructure.persistence;

import com.fiap.pagamento.infrastructure.controllers.enums.StatusPayment;
import org.ccil.cowan.tagsoup.PYXScanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentRepositoryTest {
    @Mock
    PaymentRepository paymentRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirCriarPagamento(){
        PaymentEntity pagamento = new PaymentEntity(1L, StatusPayment.APROVADO.name());

        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(pagamento);

        PaymentEntity pagamentoRetornado = paymentRepository.save(pagamento);
        //Assert
        verify(paymentRepository, times(1)).save(any(PaymentEntity.class));
        Assertions.assertEquals(pagamento, pagamentoRetornado);
    }

    @Test
    void devePermitirBuscarṔagamentoPorId(){
        PaymentEntity pagamento = new PaymentEntity(1L, StatusPayment.APROVADO.name());

        when(paymentRepository.findById(anyLong())).thenReturn(Optional.of(pagamento));

        Optional<PaymentEntity> pagamentosRetornados = paymentRepository.findById(pagamento.getPedidoId());
        verify(paymentRepository, times(1)).findById(anyLong());
        Assertions.assertEquals(Optional.of(pagamento), pagamentosRetornados);

    }

    @Test
    void devePermitirBuscarṔagamentos(){
        PaymentEntity pagamento1 = new PaymentEntity(1L, StatusPayment.APROVADO.name());
        PaymentEntity pagamento2 = new PaymentEntity(2L, StatusPayment.APROVADO.name());
        PaymentEntity pagamento3 = new PaymentEntity(3L, StatusPayment.APROVADO.name());
        List<PaymentEntity> pagamentos = new ArrayList<>();
        pagamentos.add(pagamento1);
        pagamentos.add(pagamento2);
        pagamentos.add(pagamento3);
        when(paymentRepository.findAll()).thenReturn(pagamentos);

        List<PaymentEntity> pagamentosRetornados = (List<PaymentEntity>) paymentRepository.findAll();
        verify(paymentRepository, times(1)).findAll();
        Assertions.assertEquals(pagamentos, pagamentosRetornados);

    }

    @Test
    void devePermitirDeletarPagamento(){
        paymentRepository.deleteById(any());
        verify(paymentRepository, times(1)).deleteById(any());
    }
}
