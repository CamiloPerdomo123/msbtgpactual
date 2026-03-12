package com.ms.mspactual.infrastructure;

import com.ms.mspactual.application.dto.in.fondo.FondoRequestDto;
import com.ms.mspactual.application.dto.on.transacciones.TransaccionResponse;
import com.ms.mspactual.application.port.interactor.IFondoService;
import com.ms.mspactual.application.port.interactor.ILogging;
import com.ms.mspactual.infrastructure.controller.FondoController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FondoControllerTest {

    @Mock
    private IFondoService fondoService;

    @Mock
    private ILogging log;

    @InjectMocks
    private FondoController fondoController;

    @Test
    void deberiaSuscribirFondoCorrectamente() {

        FondoRequestDto request = new FondoRequestDto(
                "1",
                "1",
                100000.0
        );

        ResponseEntity<Void> response = fondoController.suscribirFondo(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void deberiaCancelarFondoCorrectamente() {

        FondoRequestDto request = new FondoRequestDto(
                "1",
                "1",
                100000.0
        );

        ResponseEntity<Void> response = fondoController.cancelarFondo(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void deberiaRetornarHistorialTransacciones() {

        TransaccionResponse tx = new TransaccionResponse(
                "1",
                "1",
                "1",
                "APERTURA",
                100000.0,
                LocalDateTime.now()
        );

        when(fondoService.historialTransacciones("1"))
                .thenReturn(List.of(tx));

        ResponseEntity<List<TransaccionResponse>> response =
                fondoController.historial("1");

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }
}
