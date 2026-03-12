package com.ms.mspactual.infrastructure.repository.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;

    private String cedula;

    private String nombre;

    private Double saldo;

    private String preferenciaNotificacion;

    private String emailOrSms;

    private List<String> fondosSuscritos;
}
