package com.ms.mspactual.application.mapper;

import com.ms.mspactual.application.dto.in.cliente.ClienteRequestDto;
import com.ms.mspactual.domain.models.ClienteModels;
import org.springframework.stereotype.Component;

@Component
public class ClienteApplicationMapper {

    public ClienteModels toModel(ClienteRequestDto clienteRequestDto) {

        return ClienteModels.crear(
                clienteRequestDto.cedula(),
                clienteRequestDto.nombre(),
                clienteRequestDto.preferenciaNotificacion(),
                clienteRequestDto.emailOrSms()
        );
    }
}
