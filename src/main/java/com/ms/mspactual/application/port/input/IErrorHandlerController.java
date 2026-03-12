package com.ms.mspactual.application.port.input;

import com.ms.mspactual.application.ApplicationException;
import com.ms.mspactual.domain.DomainException;
import com.ms.mspactual.infrastructure.repository.persistencia.RepositoryCacheException;
import com.ms.mspactual.infrastructurecross.application.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface IErrorHandlerController {
    ResponseEntity<ErrorResponse> domainException(HttpServletRequest request, DomainException e);

    ResponseEntity<ErrorResponse> applicationException(HttpServletRequest request, ApplicationException e);

    ResponseEntity<ErrorResponse> reposotoryException(HttpServletRequest request, RepositoryCacheException e);
}
