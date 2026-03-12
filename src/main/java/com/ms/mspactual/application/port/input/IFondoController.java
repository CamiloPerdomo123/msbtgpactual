package com.ms.mspactual.application.port.input;

import com.ms.mspactual.application.dto.in.fondo.FondoRequestDto;
import com.ms.mspactual.application.dto.on.transacciones.TransaccionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFondoController {

    ResponseEntity<Void> suscribirFondo(FondoRequestDto request);

    ResponseEntity<Void> cancelarFondo(FondoRequestDto request);

    ResponseEntity<List<TransaccionResponse>> historial(String clienteId);

}
