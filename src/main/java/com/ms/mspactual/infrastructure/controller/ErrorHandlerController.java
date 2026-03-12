package com.ms.mspactual.infrastructure.controller;


import com.ms.mspactual.application.ApplicationException;
import com.ms.mspactual.application.port.input.IErrorHandlerController;
import com.ms.mspactual.application.port.interactor.ILogging;
import com.ms.mspactual.domain.DomainException;
import com.ms.mspactual.infrastructure.repository.persistencia.RepositoryCacheException;
import com.ms.mspactual.infrastructurecross.application.ErrorInfo;
import com.ms.mspactual.infrastructurecross.application.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorHandlerController implements IErrorHandlerController {

    private final ILogging log;

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus status,
            String message,
            String code,
            String uri,
            Exception e) {

        ErrorInfo errorInfo = new ErrorInfo(
                status.value(),
                message,
                code,
                uri
        );

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(errorInfo);

        log.error(
                "ERROR | Method: buildErrorResponse | Code: {} | Endpoint: {} | Message: {}",
                e,
                code,
                uri,
                message
        );


        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> domainException(HttpServletRequest request, DomainException e) {
        return buildErrorResponse(HttpStatus.valueOf(e.getStatusHttp()), e.getMessage(), e.getCodigoError(), request.getRequestURI(), e);
    }

    @Override
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationException(HttpServletRequest request, ApplicationException e) {
        return buildErrorResponse(e.getHttpStatus(), e.getMessage(), e.getCodigoError(), request.getRequestURI(), e);
    }

    @Override
    @ExceptionHandler(RepositoryCacheException.class)
    public ResponseEntity<ErrorResponse> reposotoryException(HttpServletRequest request, RepositoryCacheException e) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCodigoError(), request.getRequestURI(), e);
    }
}
