package com.fiap.pagamento.infrastructure.persistence;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "payment-table")
public class PaymentEntity {

    @DynamoDBHashKey(attributeName = "id_pedido")
    private Long pedidoId;

    @DynamoDBAttribute(attributeName = "pagamento")
    private String pagamento;

    public PaymentEntity() {
    }

    public PaymentEntity(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public PaymentEntity(Long pedidoId, String pagamento) {
        this.pedidoId = pedidoId;
        this.pagamento = pagamento;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

}
