package com.ms.mspactual.application.impl;

import com.ms.mspactual.application.ApplicationException;
import com.ms.mspactual.application.dto.in.cliente.ClienteRequestDto;
import com.ms.mspactual.application.dto.on.cliente.ClienteResponse;
import com.ms.mspactual.application.mapper.ClienteApplicationMapper;
import com.ms.mspactual.application.port.interactor.IClienteService;
import com.ms.mspactual.domain.models.ClienteModels;
import com.ms.mspactual.infrastructure.repository.entity.Cliente;
import com.ms.mspactual.infrastructure.repository.models.ClientePersistenceMapper;
import com.ms.mspactual.infrastructure.repository.persistencia.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteApplicationMapper clienteApplicationMapper;
    private final ClientePersistenceMapper clientePersistenceMapper;

    @Override
    public ClienteResponse crearCliente(ClienteRequestDto clienteRequestDto) {

        Optional<Cliente> clienteExistente = clienteRepository.findByCedula(clienteRequestDto.cedula());

        if(clienteExistente.isPresent()){
            throw new ApplicationException(
                    ErrorEnumsApplication.EXISTE_CLIENTE.getMensaje(),
                    ErrorEnumsApplication.EXISTE_CLIENTE.getCodigo(),
                    HttpStatus.CONFLICT
            );
        }

        ClienteModels clienteModel = clienteApplicationMapper.toModel(clienteRequestDto);

        Cliente cliente = clientePersistenceMapper.toEntity(clienteModel);

        clienteRepository.save(cliente);

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre()
        );
    }
}
