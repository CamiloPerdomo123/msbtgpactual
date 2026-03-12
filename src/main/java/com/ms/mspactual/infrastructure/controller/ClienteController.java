package com.ms.mspactual.infrastructure.controller;

import com.ms.mspactual.application.dto.in.cliente.ClienteRequestDto;
import com.ms.mspactual.application.dto.on.cliente.ClienteResponse;
import com.ms.mspactual.application.port.input.IClienteController;
import com.ms.mspactual.application.port.interactor.IClienteService;
import com.ms.mspactual.application.port.interactor.ILogging;
import com.ms.mspactual.infrastructurecross.metricas.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController implements IClienteController {

    private final IClienteService clienteService;
    private final ILogging logging;

    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteRequestDto cliente) {

        logging.info("INICIO | Classname: {} | Method: create | cedula: {}",
                this.getClass().getSimpleName(),
                cliente.cedula());

        Instant inicio = Instant.now();

        ClienteResponse response = clienteService.crearCliente(cliente);

        Instant fin = Instant.now();
        Duration duracion = Duration.between(inicio, fin);

        logging.info("FIN | Classname: {} | Method: create | cedula: {} | tiempo respuesta: {} ms | Cliente Creado Con Exito: {}",
                this.getClass().getSimpleName(),
                cliente.cedula(),
                duracion.toMillis(),
                JsonUtil.getInstance().toJson(response)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}