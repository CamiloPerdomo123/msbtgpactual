package com.ms.mspactual.infrastructure.repository.persistencia;

import com.ms.mspactual.infrastructure.repository.entity.Fondo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FondoRepository extends MongoRepository<Fondo,String> {
}
