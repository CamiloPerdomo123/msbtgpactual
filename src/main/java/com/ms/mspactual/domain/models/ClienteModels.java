package com.ms.mspactual.domain.models;

import java.util.List;
import java.util.UUID;

public record ClienteModels(

        String id,
        String cedula,
        String nombre,
        Double saldo,
        String preferenciaNotificacion,
        String emailOrSms,
        List<String> fondosSuscritos

) {

    public static ClienteModels crear(
            String cedula,
            String nombre,
            String preferenciaNotificacion,
            String emailOrSms
    ) {

        return new ClienteModels(
                UUID.randomUUID().toString(),
                cedula,
                nombre,
                500000.0,
                preferenciaNotificacion,
                emailOrSms,
                null

        );
    }
}