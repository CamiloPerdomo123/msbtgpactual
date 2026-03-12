package com.ms.mspactual.application.port.interactor;

import com.ms.mspactual.application.dto.in.cliente.ClienteRequestDto;
import com.ms.mspactual.application.dto.on.cliente.ClienteResponse;
import com.ms.mspactual.infrastructure.repository.entity.Cliente;

public interface IClienteService {
    ClienteResponse crearCliente(ClienteRequestDto cliente);
}
