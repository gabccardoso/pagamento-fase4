package com.fiap.pagamento.infrastructure.controllers;

import com.fiap.pagamento.application.useCases.PaymentInteractor;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {

    private PaymentInteractor paymentInteractor;
    private HttpServletRequest request;
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentInteractor = mock(PaymentInteractor.class);
        request = mock(HttpServletRequest.class);
        paymentController = new PaymentController(paymentInteractor);
    }

    @Test
    public void testBuscarStatusPagamento() {
        // Mockando o comportamento do PaymentInteractor
        when(paymentInteractor.findPaymentStatus(1L)).thenReturn("APROVADO");

        // Chamando o método do controlador
        ResponseEntity<String> response = paymentController.buscarStatusPagamento(1L);

        // Verificando se o status code e o corpo da resposta estão corretos
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("APROVADO", response.getBody());
    }

    @Test
    public void testFazPagamento() {
        // Mockando o comportamento do PaymentInteractor
        when(paymentInteractor.createPayment(1L)).thenReturn("APROVADO");
        // Chamando o método do controlador
        ResponseEntity<String> response = paymentController.fazPagamento(1L);

        // Verificando se o status code e o corpo da resposta estão corretos
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("APROVADO", response.getBody());
    }
}
