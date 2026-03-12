package com.ms.mspactual.application.port.input;

import com.ms.mspactual.application.dto.in.cliente.ClienteRequestDto;
import com.ms.mspactual.application.dto.on.cliente.ClienteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IClienteController {

    ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteRequestDto cliente);
}
