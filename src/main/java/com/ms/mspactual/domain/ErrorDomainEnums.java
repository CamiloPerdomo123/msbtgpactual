package com.ms.mspactual.domain;

import lombok.Getter;

@Getter
public enum ErrorDomainEnums {

    SI_SALDO_FONDO("03","Error, No tiene saldo disponible para vincularse al fondo "),
    ;

    private final String codigo;
    private final String mensaje;

    ErrorDomainEnums(String codigo, String mensaje) {
        this.codigo = "MSPACTUAL" + codigo;
        this.mensaje = mensaje;
    }
}
