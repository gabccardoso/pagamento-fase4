package com.fiap.pagamento.main;

import com.fiap.pagamento.application.gateways.PaymentGateway;
import com.fiap.pagamento.application.useCases.PaymentInteractor;
import com.fiap.pagamento.infrastructure.gateways.PaymentRepositoryGateway;
import com.fiap.pagamento.infrastructure.gateways.gerenciadores.GerenciadorRabbit;
import com.fiap.pagamento.infrastructure.persistence.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class PaymentConfig {

    @Bean
    PaymentGateway paymentGateway(PaymentRepository paymentRepository, GerenciadorRabbit gerenciadorRabbit){
        return new PaymentRepositoryGateway(paymentRepository, gerenciadorRabbit);
    }

    @Bean
    PaymentInteractor paymentInteractor(PaymentGateway paymentGateway){
        return new PaymentInteractor(paymentGateway);
    }

    @Bean("httpClientPagamento")
    public HttpClient httpClientBean(){
        return HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS).build();
    }
}
