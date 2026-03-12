package com.ms.mspactual.domain;

import com.ms.mspactual.domain.DomainException;
import com.ms.mspactual.domain.models.FondoModels;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FondoModelsTest {

    @Test
    void validarMontoDeberiaPasarCuandoMontoEsMayorAlMinimo() {

        FondoModels fondo = new FondoModels(
                "1",
                "Fondo Test",
                75000.0,
                "FPV"
        );

        assertDoesNotThrow(() -> fondo.validarMonto(100000.0));
    }

    @Test
    void validarMontoDeberiaLanzarExcepcionCuandoMontoEsMenor() {

        FondoModels fondo = new FondoModels(
                "1",
                "Fondo Test",
                75000.0,
                "FPV"
        );

        assertThrows(DomainException.class, () -> {
            fondo.validarMonto(50000.0);
        });
    }

    @Test
    void validarSaldoClienteDeberiaPasarCuandoSaldoEsValido() {

        FondoModels fondo = new FondoModels(
                "1",
                "Fondo Test",
                75000.0,
                "FPV"
        );

        assertDoesNotThrow(() -> fondo.validarSaldoCliente(1000.0));
    }

    @Test
    void validarSaldoClienteDeberiaLanzarExcepcionCuandoSaldoEsNegativo() {

        FondoModels fondo = new FondoModels(
                "1",
                "Fondo Test",
                75000.0,
                "FPV"
        );

        assertThrows(DomainException.class, () -> {
            fondo.validarSaldoCliente(-1.0);
        });
    }

    @Test
    void devolverMontoDeberiaSumarMontoInvertidoYSaldoCliente() {

        FondoModels fondo = new FondoModels(
                "1",
                "Fondo Test",
                75000.0,
                "FPV"
        );

        Double resultado = fondo.devolverMonto(200000.0, 300000.0);

        assertEquals(500000.0, resultado);
    }
}
