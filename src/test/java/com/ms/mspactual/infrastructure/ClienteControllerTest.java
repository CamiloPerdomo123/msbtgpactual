package com.ms.mspactual.infrastructure;

import com.ms.mspactual.application.dto.in.cliente.ClienteRequestDto;
import com.ms.mspactual.application.dto.on.cliente.ClienteResponse;
import com.ms.mspactual.application.port.interactor.IClienteService;
import com.ms.mspactual.application.port.interactor.ILogging;
import com.ms.mspactual.infrastructure.controller.ClienteController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private IClienteService clienteService;

    @Mock
    private ILogging logging;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void deberiaCrearClienteCorrectamente() {

        ClienteRequestDto request = new ClienteRequestDto(
                "123456",
                "Juan Camilo",
                "EMAIL",
                "juan@mail.com"
        );

        ClienteResponse responseMock = new ClienteResponse(
                "1",
                "Juan Camilo"
        );

        when(clienteService.crearCliente(request))
                .thenReturn(responseMock);

        ResponseEntity<ClienteResponse> response =
                clienteController.crearCliente(request);

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
    }
}
