package com.ms.mspactual.infrastructure.repository.persistencia;

import com.ms.mspactual.infrastructure.repository.entity.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransaccionRepository extends MongoRepository<Transaccion, String> {

    List<Transaccion> findByClienteId(String clienteId);

    List<Transaccion> findByClienteIdAndFondoIdAndTipo(
            String clienteId,
            String fondoId,
            String tipo
    );

}
