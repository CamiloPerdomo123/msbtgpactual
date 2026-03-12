package com.ms.mspactual.application;

import com.ms.mspactual.application.dto.in.cliente.ClienteRequestDto;
import com.ms.mspactual.application.mapper.ClienteApplicationMapper;
import com.ms.mspactual.domain.models.ClienteModels;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteApplicationMapperTest {

    private final ClienteApplicationMapper mapper = new ClienteApplicationMapper();

    @Test
    void deberiaMapearClienteRequestDtoAClienteModels() {

        ClienteRequestDto request = new ClienteRequestDto(
                "123456",
                "Juan Camilo",
                "EMAIL",
                "juan@mail.com"
        );

        ClienteModels model = mapper.toModel(request);

        assertNotNull(model);
        assertEquals("123456", model.cedula());
        assertEquals("Juan Camilo", model.nombre());
        assertEquals("EMAIL", model.preferenciaNotificacion());
        assertEquals("juan@mail.com", model.emailOrSms());
    }
}
