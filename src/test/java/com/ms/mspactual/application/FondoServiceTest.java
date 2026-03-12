package com.ms.mspactual.application;

import com.ms.mspactual.application.dto.in.fondo.FondoRequestDto;
import com.ms.mspactual.application.dto.on.transacciones.TransaccionResponse;
import com.ms.mspactual.application.impl.FondoService;
import com.ms.mspactual.infrastructure.repository.entity.Cliente;
import com.ms.mspactual.infrastructure.repository.entity.Fondo;
import com.ms.mspactual.infrastructure.repository.entity.Transaccion;
import com.ms.mspactual.infrastructure.repository.persistencia.ClienteRepository;
import com.ms.mspactual.infrastructure.repository.persistencia.FondoRepository;
import com.ms.mspactual.infrastructure.repository.persistencia.TransaccionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FondoServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private FondoRepository fondoRepository;

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private FondoService fondoService;

    @Test
    void deberiaSuscribirFondoCorrectamente() {

        FondoRequestDto request = new FondoRequestDto("1","1",100000.0);

        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setNombre("Juan");
        cliente.setSaldo(500000.0);
        cliente.setPreferenciaNotificacion("EMAIL");
        cliente.setFondosSuscritos(new ArrayList<>());

        Fondo fondo = new Fondo("1","Fondo Test",75000.0,"FPV");

        when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente));
        when(fondoRepository.findById("1")).thenReturn(Optional.of(fondo));

        fondoService.suscribirFondo(request);

        verify(clienteRepository).save(cliente);
        verify(transaccionRepository).save(any(Transaccion.class));

        assertEquals(400000.0, cliente.getSaldo());
        assertTrue(cliente.getFondosSuscritos().contains("1"));
    }

    @Test
    void deberiaCancelarFondoCorrectamente() {

        FondoRequestDto request = new FondoRequestDto("1","1",100000.0);

        Cliente cliente = new Cliente();
        cliente.setId("1");
        cliente.setSaldo(400000.0);
        cliente.setFondosSuscritos(new ArrayList<>(List.of("1")));

        Fondo fondo = new Fondo("1","Fondo Test",75000.0,"FPV");

        Transaccion apertura = new Transaccion();
        apertura.setMonto(100000.0);

        when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente));
        when(fondoRepository.findById("1")).thenReturn(Optional.of(fondo));
        when(transaccionRepository.findByClienteIdAndFondoIdAndTipo("1","1","APERTURA"))
                .thenReturn(List.of(apertura));

        fondoService.cancelarFondo(request);

        verify(clienteRepository).save(cliente);
        verify(transaccionRepository).save(any(Transaccion.class));

        assertEquals(500000.0, cliente.getSaldo());
        assertFalse(cliente.getFondosSuscritos().contains("1"));
    }

    @Test
    void deberiaRetornarHistorialTransacciones() {

        Cliente cliente = new Cliente();
        cliente.setId("1");

        Transaccion tx = new Transaccion();
        tx.setId("1");
        tx.setClienteId("1");
        tx.setFondoId("1");
        tx.setTipo("APERTURA");
        tx.setMonto(100000.0);
        tx.setFecha(LocalDateTime.now());

        when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente));
        when(transaccionRepository.findByClienteId("1")).thenReturn(List.of(tx));

        List<TransaccionResponse> response = fondoService.historialTransacciones("1");

        assertEquals(1, response.size());
        assertEquals("1", response.get(0).clienteId());

        verify(transaccionRepository).findByClienteId("1");
    }

}
