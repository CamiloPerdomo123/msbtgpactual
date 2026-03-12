package com.ms.mspactual.application.dto.in.fondo;

public record FondoRequestDto(
        String clienteId,
        String fondoId,
        Double monto
) {
}
