package com.fiap.pagamento.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
}
