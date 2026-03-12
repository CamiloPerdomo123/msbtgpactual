package com.ms.mspactual.infrastructure;

import com.ms.mspactual.domain.models.ClienteModels;
import com.ms.mspactual.infrastructure.repository.entity.Cliente;
import com.ms.mspactual.infrastructure.repository.models.ClientePersistenceMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientePersistenceMapperTest {

    private final ClientePersistenceMapper mapper = new ClientePersistenceMapper();

    @Test
    void deberiaConvertirClienteModelsAClienteEntity() {

        ClienteModels model = new ClienteModels(
                "1",
                "123456",
                "Juan Camilo",
                500000.0,
                "EMAIL",
                "juan@mail.com",
                List.of()
        );

        Cliente entity = mapper.toEntity(model);

        assertNotNull(entity);
        assertEquals("1", entity.getId());
        assertEquals("Juan Camilo", entity.getNombre());
        assertEquals("123456", entity.getCedula());
        assertEquals(500000.0, entity.getSaldo());
        assertEquals("EMAIL", entity.getPreferenciaNotificacion());
        assertEquals("juan@mail.com", entity.getEmailOrSms());

        assertNotNull(entity.getFondosSuscritos());
        assertTrue(entity.getFondosSuscritos().isEmpty());
    }
}
