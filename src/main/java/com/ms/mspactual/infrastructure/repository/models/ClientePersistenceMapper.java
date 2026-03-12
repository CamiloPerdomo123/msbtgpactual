package com.ms.mspactual.infrastructure.repository.models;

import com.ms.mspactual.domain.models.ClienteModels;
import com.ms.mspactual.infrastructure.repository.entity.Cliente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ClientePersistenceMapper {

    public Cliente toEntity(ClienteModels clienteModels) {
        return Cliente.builder()
                .id(clienteModels.id())
                .nombre(clienteModels.nombre())
                .saldo(clienteModels.saldo())
                .cedula(clienteModels.cedula())
                .preferenciaNotificacion(clienteModels.preferenciaNotificacion())
                .emailOrSms(clienteModels.emailOrSms())
                .fondosSuscritos(new ArrayList<>())
                .build();
    }
}
