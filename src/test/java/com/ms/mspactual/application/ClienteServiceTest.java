package com.ms.mspactual.application;

import com.ms.mspactual.application.dto.in.cliente.ClienteRequestDto;
import com.ms.mspactual.application.dto.on.cliente.ClienteResponse;
import com.ms.mspactual.application.impl.ClienteService;
import com.ms.mspactual.application.mapper.ClienteApplicationMapper;
import com.ms.mspactual.domain.models.ClienteModels;
import com.ms.mspactual.infrastructure.repository.entity.Cliente;
import com.ms.mspactual.infrastructure.repository.models.ClientePersistenceMapper;
import com.ms.mspactual.infrastructure.repository.persistencia.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteApplicationMapper clienteApplicationMapper;

    @Mock
    private ClientePersistenceMapper clientePersistenceMapper;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void deberiaCrearClienteCorrectamente() {

        ClienteRequestDto request = new ClienteRequestDto("123", "Juan", "", "");

        ClienteModels cliente = new ClienteModels(
                "1",
                "123456",
                "Juan Camilo",
                500000.0,
                "EMAIL",
                "juan@mail.com",
                List.of()
        );

        Cliente clienteEntity = new Cliente();
        clienteEntity.setId("1");
        clienteEntity.setNombre("Juan");

        when(clienteRepository.existsByCedula("123"))
                .thenReturn(false);

        when(clienteApplicationMapper.toModel(request))
                .thenReturn(cliente);

        when(clientePersistenceMapper.toEntity(cliente))
                .thenReturn(clienteEntity);

        when(clienteRepository.save(clienteEntity))
                .thenReturn(clienteEntity);

        ClienteResponse response = clienteService.crearCliente(request);

        assertNotNull(response);
        assertEquals("1", response.idCliente());
        assertEquals("Juan", response.nombre());

        verify(clienteRepository).existsByCedula("123");
        verify(clienteRepository).save(clienteEntity);
    }

    @Test
    void deberiaLanzarExcepcionSiClienteExiste() {

        ClienteRequestDto request = new ClienteRequestDto("123", "Juan", "Email", "jperdomo@gmail.com");

        when(clienteRepository.existsByCedula("123"))
                .thenReturn(true);

        ApplicationException exception = assertThrows(ApplicationException.class,
                () -> clienteService.crearCliente(request));

        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());

        verify(clienteRepository).existsByCedula("123");
        verify(clienteRepository, never()).save(any());
    }
}
