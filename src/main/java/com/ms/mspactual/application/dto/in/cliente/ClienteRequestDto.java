package com.ms.mspactual.application.dto.in.cliente;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDto(
        @NotBlank(message = "Cedula es obligatorio")
        String cedula,

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "Preferencia de Notificacion es obligatorio")
        String preferenciaNotificacion,

        @NotBlank(message = "Preferencia de Notificacion es obligatorio")
        String emailOrSms
) {
}
