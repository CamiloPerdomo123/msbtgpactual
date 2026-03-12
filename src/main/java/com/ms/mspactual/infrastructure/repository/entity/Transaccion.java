package com.ms.mspactual.infrastructure.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "transacciones")
public class Transaccion {

    @Id
    private String id;

    private String clienteId;

    private String fondoId;

    private String tipo;

    private Double monto;

    private LocalDateTime fecha;
}
