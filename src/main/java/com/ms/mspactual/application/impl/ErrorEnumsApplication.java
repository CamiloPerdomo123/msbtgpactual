package com.ms.mspactual.application.impl;

import lombok.Getter;

@Getter
public enum ErrorEnumsApplication {

    EXISTE_CLIENTE("01","Error, el cliente ya existente"),
    ERROR_CLIENTE_NOT_FOUND("02","Error, el cliente no existe"),
    SI_SALDO_FONDO("03","Error, No tiene saldo disponible para vincularse al fondo "),
    CLIENTE_SIN_TRANSACCION("04","Error, El cliente no tiene transacciones registradas"),
    ;
    private final String codigo;
    private final String mensaje;

    ErrorEnumsApplication(String codigo, String mensaje) {
        this.codigo = "MSPACTUAL" + codigo;
        this.mensaje = mensaje;
    }
}
