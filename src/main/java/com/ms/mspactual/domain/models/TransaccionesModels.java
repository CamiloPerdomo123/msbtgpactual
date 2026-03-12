package com.ms.mspactual.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransaccionesModels(
        String id,
        String clienteId,
        String fondoId,
        String tipo,
        Double monto,
        LocalDateTime fecha
) {

    public static TransaccionesModels crear(
            String clienteId,
            String fondoId,
            String tipo,
            Double monto
    ) {

        return new TransaccionesModels(
                UUID.randomUUID().toString(),
                clienteId,
                fondoId,
                tipo,
                monto,
                LocalDateTime.now()
        );
    }
}
