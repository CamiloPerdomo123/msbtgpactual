package com.ms.mspactual.infrastructure.repository.persistencia;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class RepositoryCacheException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7405432357544406448L;

    private final String codigoError;

    public RepositoryCacheException(String errorMessage, String codigoError){
        super(errorMessage);
        this.codigoError = codigoError;
    }

}