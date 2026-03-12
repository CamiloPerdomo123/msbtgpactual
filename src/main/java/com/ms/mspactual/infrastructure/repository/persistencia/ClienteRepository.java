package com.ms.mspactual.infrastructure.repository.persistencia;

import com.ms.mspactual.infrastructure.repository.entity.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ClienteRepository extends MongoRepository<Cliente,String> {
    boolean existsByCedula(String cedula);
}
