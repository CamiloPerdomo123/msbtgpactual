package com.ms.mspactual.application;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ApplicationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7405432357544406448L;
    
    private final HttpStatus httpStatus;    
    private final String codigoError;

    public ApplicationException(String errorMessage, String codigoError, HttpStatus httpStatus){
        super(errorMessage);
        this.codigoError = codigoError;
        this.httpStatus = httpStatus;
    } 

}