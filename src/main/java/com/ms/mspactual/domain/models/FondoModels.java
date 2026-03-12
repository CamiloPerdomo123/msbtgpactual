package com.ms.mspactual.domain.models;

import com.ms.mspactual.domain.DomainException;
import com.ms.mspactual.domain.ErrorDomainEnums;
import org.springframework.http.HttpStatus;

public record FondoModels(
        String id,
        String nombre,
        Double montoMinimo,
        String categoria
) {
    public void validarMonto(Double monto) {

        if (monto < montoMinimo) {
            throw new DomainException(
                    ErrorDomainEnums.SI_SALDO_FONDO.getMensaje() + nombre,
                    ErrorDomainEnums.SI_SALDO_FONDO.getCodigo(),
                    HttpStatus.BAD_REQUEST.value()
            );
        }

    }

    public void validarSaldoCliente(Double monto) {

        if (monto < 0) {
            throw new DomainException(
                    ErrorDomainEnums.SI_SALDO_FONDO.getMensaje() + nombre,
                    ErrorDomainEnums.SI_SALDO_FONDO.getCodigo(),
                    HttpStatus.BAD_REQUEST.value()
            );
        }

    }

    public Double devolverMonto(Double montoInvertido, Double montoCliente) {

        return montoInvertido + montoCliente;
    }
}
