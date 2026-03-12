package com.ms.mspactual.application.port.interactor;

import com.ms.mspactual.application.dto.in.fondo.FondoRequestDto;
import com.ms.mspactual.application.dto.on.transacciones.TransaccionResponse;
import com.ms.mspactual.infrastructure.repository.entity.Transaccion;

import java.util.List;

public interface IFondoService {

    void suscribirFondo(FondoRequestDto request);

    void cancelarFondo(FondoRequestDto request);

    List<TransaccionResponse> historialTransacciones(String clienteId);
}
