package com.ms.mspactual.domain;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class DomainException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -7405432357544406448L;
     
    private final String codigoError;

    private final int statusHttp;

    public DomainException(String errorMessage, String codigoError, int statusHttp){
        super(errorMessage);
        this.codigoError = codigoError;
        this.statusHttp = statusHttp;
    }
    
}