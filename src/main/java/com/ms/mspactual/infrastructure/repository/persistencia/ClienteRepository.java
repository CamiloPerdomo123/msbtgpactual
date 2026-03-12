package com.ms.mspactual.infrastructure.repository.persistencia;

import com.ms.mspactual.infrastructure.repository.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepository extends MongoRepository<Cliente,String> {
    Optional<Cliente> findByCedula(String cedula);
}
