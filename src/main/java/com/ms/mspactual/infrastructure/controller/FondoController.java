package com.ms.mspactual.infrastructure.controller;

import com.ms.mspactual.application.dto.in.fondo.FondoRequestDto;
import com.ms.mspactual.application.dto.on.transacciones.TransaccionResponse;
import com.ms.mspactual.application.port.input.IFondoController;
import com.ms.mspactual.application.port.interactor.IFondoService;
import com.ms.mspactual.application.port.interactor.ILogging;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/fondos")
@RequiredArgsConstructor
public class FondoController implements IFondoController {

    private final IFondoService fondoService;
    private final ILogging log;

    @Override
    @PostMapping("/suscribir")
    public ResponseEntity<Void> suscribirFondo(@RequestBody FondoRequestDto request) {

        log.info("INICIO | Classname: {} | Method: suscribirFondo | clienteId: {} | fondoId: {}",
                this.getClass().getSimpleName(),
                request.clienteId(),
                request.fondoId());

        Instant inicio = Instant.now();

        fondoService.suscribirFondo(request);

        Instant fin = Instant.now();
        Duration duracion = Duration.between(inicio, fin);

        log.info("FIN | Classname: {} | Method: suscribirFondo | clienteId: {} | fondoId: {} | tiempo respuesta: {} ms",
                this.getClass().getSimpleName(),
                request.clienteId(),
                request.fondoId(),
                duracion.toMillis());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @PostMapping("/cancelar")
    public ResponseEntity<Void> cancelarFondo(@RequestBody FondoRequestDto request) {

        log.info("INICIO | Classname: {} | Method: cancelarFondo | clienteId: {} | fondoId: {}",
                this.getClass().getSimpleName(),
                request.clienteId(),
                request.fondoId());

        Instant inicio = Instant.now();

        fondoService.cancelarFondo(request);

        Instant fin = Instant.now();
        Duration duracion = Duration.between(inicio, fin);

        log.info("FIN | Classname: {} | Method: cancelarFondo | clienteId: {} | fondoId: {} | tiempo respuesta: {} ms",
                this.getClass().getSimpleName(),
                request.clienteId(),
                request.fondoId(),
                duracion.toMillis());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/transacciones/{clienteId}")
    public ResponseEntity<List<TransaccionResponse>> historial(@PathVariable String clienteId) {

        log.info("INICIO | Controller: {} | Method: historial | clienteId: {}",
                this.getClass().getSimpleName(),
                clienteId);

        Instant inicio = Instant.now();

        List<TransaccionResponse> response = fondoService.historialTransacciones(clienteId);

        long duracion = Duration.between(inicio, Instant.now()).toMillis();

        log.info("FIN | Controller: {} | Method: historial | clienteId: {} | tiempo: {} ms",
                this.getClass().getSimpleName(),
                clienteId,
                duracion);

        return ResponseEntity.ok(response);
    }
}
