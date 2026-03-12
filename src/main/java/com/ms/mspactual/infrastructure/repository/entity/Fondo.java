package com.ms.mspactual.infrastructure.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fondos")
public class Fondo {

    @Id
    private String id;

    private String nombre;

    private Double montoMinimo;

    private String categoria;
}
