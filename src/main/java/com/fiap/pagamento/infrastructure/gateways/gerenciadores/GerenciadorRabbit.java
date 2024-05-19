package com.fiap.pagamento.infrastructure.gateways.gerenciadores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.pagamento.infrastructure.controllers.enums.StatusPayment;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class GerenciadorRabbit {

    private final static String QUEUE_NAME = "pedidos-pagos";

    public void ColocaPedidoNaFilaDePagos(Long idPedido) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("ec2-54-173-136-137.compute-1.amazonaws.com");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            MensagemDTO mensagemDTO = new MensagemDTO(idPedido, StatusPayment.APROVADO);
            ObjectMapper objectMapper = new ObjectMapper();
            String mensagem = objectMapper.writeValueAsString(mensagemDTO);

            // Publica a mensagem na fila
            channel.basicPublish("", QUEUE_NAME, null, mensagem.getBytes());
        } catch (TimeoutException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
