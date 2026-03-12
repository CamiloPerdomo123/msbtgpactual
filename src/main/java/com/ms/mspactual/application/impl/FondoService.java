package com.ms.mspactual.application.impl;

import com.ms.mspactual.application.ApplicationException;
import com.ms.mspactual.application.dto.in.fondo.FondoRequestDto;
import com.ms.mspactual.application.dto.on.transacciones.TransaccionResponse;
import com.ms.mspactual.application.port.interactor.IFondoService;
import com.ms.mspactual.domain.models.FondoModels;
import com.ms.mspactual.domain.models.TransaccionesModels;
import com.ms.mspactual.infrastructure.repository.entity.Cliente;
import com.ms.mspactual.infrastructure.repository.entity.Fondo;
import com.ms.mspactual.infrastructure.repository.entity.Transaccion;
import com.ms.mspactual.infrastructure.repository.persistencia.ClienteRepository;
import com.ms.mspactual.infrastructure.repository.persistencia.FondoRepository;
import com.ms.mspactual.infrastructure.repository.persistencia.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FondoService implements IFondoService {

    private static final String APERTURA = "APERTURA";
    private static final String CANCELACION = "CANCELACION";

    private final ClienteRepository clienteRepository;
    private final FondoRepository fondoRepository;
    private final TransaccionRepository transaccionRepository;

    @Override
    public void suscribirFondo(FondoRequestDto request) {

        Cliente cliente = obtenerCliente(request.clienteId());
        Fondo fondo = obtenerFondo(request.fondoId());

        FondoModels fondoModel = fondoModel(fondo);

        this.validarSuscripcion(cliente, request, fondoModel);

        this.actualizarCliente(cliente, request);

        this.registrarTransaccion(request, APERTURA);

        this.enviarNotificacion(cliente, fondo);
    }

    @Override
    public void cancelarFondo(FondoRequestDto request) {

        Cliente cliente = obtenerCliente(request.clienteId());
        Fondo fondo = obtenerFondo(request.fondoId());

        Double montoInvertido = calcularMontoInvertido(request);

        FondoModels fondoModel = construirFondoModel(fondo);

        Double nuevoSaldo = fondoModel.devolverMonto(montoInvertido, cliente.getSaldo());

        actualizarCliente(cliente, request.fondoId(), nuevoSaldo);

        registrarTransaccion(request, CANCELACION);
    }

    @Override
    public List<TransaccionResponse> historialTransacciones(String clienteId) {

        Cliente cliente = obtenerCliente(clienteId);

        List<Transaccion> transacciones = obtenerTransaccionesCliente(cliente.getId());

        return transacciones.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private Cliente obtenerCliente(String clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ApplicationException(
                        ErrorEnumsApplication.ERROR_CLIENTE_NOT_FOUND.getMensaje(),
                        ErrorEnumsApplication.ERROR_CLIENTE_NOT_FOUND.getCodigo(),
                        HttpStatus.NO_CONTENT
                ));
    }

    private Fondo obtenerFondo(String fondoId) {
        return fondoRepository.findById(fondoId)
                .orElseThrow(() -> new ApplicationException(
                        ErrorEnumsApplication.ERROR_CLIENTE_NOT_FOUND.getMensaje(),
                        ErrorEnumsApplication.ERROR_CLIENTE_NOT_FOUND.getCodigo(),
                        HttpStatus.NO_CONTENT
                ));
    }

    private void validarSuscripcion(Cliente cliente, FondoRequestDto request, FondoModels fondoModel) {

        Double saldoRestante = cliente.getSaldo() - request.monto();

        fondoModel.validarMonto(request.monto());
        fondoModel.validarSaldoCliente(saldoRestante);
    }

    private void actualizarCliente(Cliente cliente, FondoRequestDto request) {

        Double nuevoSaldo = cliente.getSaldo() - request.monto();

        cliente.setSaldo(nuevoSaldo);
        cliente.getFondosSuscritos().add(request.fondoId());

        clienteRepository.save(cliente);
    }

    private FondoModels fondoModel(Fondo fondo) {
        return new FondoModels(
                fondo.getId(),
                fondo.getNombre(),
                fondo.getMontoMinimo(),
                fondo.getCategoria()
        );
    }


    private void registrarTransaccion(FondoRequestDto request, String tipo) {

        TransaccionesModels modelo = TransaccionesModels.crear(
                request.clienteId(),
                request.fondoId(),
                tipo,
                request.monto()
        );

        Transaccion entity = new Transaccion();

        entity.setId(modelo.id());
        entity.setClienteId(modelo.clienteId());
        entity.setFondoId(modelo.fondoId());
        entity.setTipo(modelo.tipo());
        entity.setMonto(modelo.monto());
        entity.setFecha(modelo.fecha());

        transaccionRepository.save(entity);
    }

    private TransaccionResponse mapToResponse(Transaccion tx) {

        return new TransaccionResponse(
                tx.getId(),
                tx.getClienteId(),
                tx.getFondoId(),
                tx.getTipo(),
                tx.getMonto(),
                tx.getFecha()
        );
    }

    private Double calcularMontoInvertido(FondoRequestDto request) {

        List<Transaccion> aperturas = transaccionRepository
                .findByClienteIdAndFondoIdAndTipo(
                        request.clienteId(),
                        request.fondoId(),
                        APERTURA
                );

        return aperturas.stream()
                .mapToDouble(Transaccion::getMonto)
                .sum();
    }

    private FondoModels construirFondoModel(Fondo fondo) {

        return new FondoModels(
                fondo.getId(),
                fondo.getNombre(),
                fondo.getMontoMinimo(),
                fondo.getCategoria()
        );
    }

    private void actualizarCliente(Cliente cliente, String fondoId, Double nuevoSaldo) {

        cliente.setSaldo(nuevoSaldo);
        cliente.getFondosSuscritos().remove(fondoId);

        clienteRepository.save(cliente);
    }

    private List<Transaccion> obtenerTransaccionesCliente(String clienteId) {

        List<Transaccion> transacciones = transaccionRepository.findByClienteId(clienteId);

        if (transacciones.isEmpty()) {
            throw new ApplicationException(
                    ErrorEnumsApplication.CLIENTE_SIN_TRANSACCION.getMensaje(),
                    ErrorEnumsApplication.CLIENTE_SIN_TRANSACCION.getCodigo(),
                    HttpStatus.NO_CONTENT
            );
        }

        return transacciones;
    }

    private void enviarNotificacion(Cliente cliente, Fondo fondo) {

        if ("EMAIL".equalsIgnoreCase(cliente.getPreferenciaNotificacion())) {
            System.out.println("EMAIL enviado a "
                    + cliente.getNombre()
                    + " operación "
                    + APERTURA
                    + " fondo "
                    + fondo.getNombre());
        } else {
            System.out.println("SMS enviado a "
                    + cliente.getNombre()
                    + " operación "
                    + APERTURA
                    + " fondo "
                    + fondo.getNombre());
        }
    }
}
