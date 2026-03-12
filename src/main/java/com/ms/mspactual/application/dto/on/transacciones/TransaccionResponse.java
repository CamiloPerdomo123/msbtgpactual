package com.ms.mspactual.application.dto.on.transacciones;

import java.time.LocalDateTime;

public record TransaccionResponse(
        String id,
        String clienteId,
        String fondoId,
        String tipo,
        Double monto,
        LocalDateTime fecha
) {
}
