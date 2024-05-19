package com.fiap.pagamento.infrastructure.gateways.gerenciadores;

import com.fiap.pagamento.infrastructure.controllers.enums.StatusPayment;

public class MensagemDTO {

    private Long idPedido;
    private StatusPayment statusPayment;

    public MensagemDTO() {
    }

    public MensagemDTO(Long idPedido, StatusPayment statusPayment) {
        this.idPedido = idPedido;
        this.statusPayment = statusPayment;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }
}
