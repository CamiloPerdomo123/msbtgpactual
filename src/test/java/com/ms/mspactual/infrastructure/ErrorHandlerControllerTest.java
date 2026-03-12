package com.ms.mspactual.infrastructure;

import com.ms.mspactual.application.ApplicationException;
import com.ms.mspactual.application.port.interactor.ILogging;
import com.ms.mspactual.domain.DomainException;
import com.ms.mspactual.infrastructure.controller.ErrorHandlerController;
import com.ms.mspactual.infrastructure.repository.persistencia.RepositoryCacheException;
import com.ms.mspactual.infrastructurecross.application.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ErrorHandlerControllerTest {

    @Mock
    private ILogging logging;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private ErrorHandlerController errorHandlerController;

    @BeforeEach
    void setup() {
        when(request.getRequestURI()).thenReturn("/api/test");
    }

    @Test
    void deberiaManejarDomainException() {

        DomainException exception =
                new DomainException("Error dominio", "D001", 400);

        ResponseEntity<ErrorResponse> response =
                errorHandlerController.domainException(request, exception);

        assertAll(
                () -> assertEquals(400, response.getStatusCode().value()),
                () -> assertEquals("Error dominio", Objects.requireNonNull(response.getBody()).getError().getMessage()),
                () -> assertEquals("D001", Objects.requireNonNull(response.getBody()).getError().getCode()),
                () -> assertEquals("/api/test", Objects.requireNonNull(response.getBody()).getError().getPath())
        );

        verify(logging).error(anyString(), eq(exception), eq("D001"), eq("/api/test"), eq("Error dominio"));
    }

    @Test
    void deberiaManejarApplicationException() {

        ApplicationException exception =
                new ApplicationException("Error app", "A001", HttpStatus.BAD_REQUEST);

        ResponseEntity<ErrorResponse> response =
                errorHandlerController.applicationException(request, exception);

        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals("Error app", Objects.requireNonNull(response.getBody()).getError().getMessage()),
                () -> assertEquals("A001", Objects.requireNonNull(response.getBody()).getError().getCode())
        );

        verify(logging).error(anyString(), eq(exception), eq("A001"), eq("/api/test"), eq("Error app"));
    }

    @Test
    void deberiaManejarRepositoryException() {

        RepositoryCacheException exception =
                new RepositoryCacheException("Error repo", "R001");

        ResponseEntity<ErrorResponse> response =
                errorHandlerController.reposotoryException(request, exception);

        assertAll(
                () -> assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals("Error repo", Objects.requireNonNull(response.getBody()).getError().getMessage()),
                () -> assertEquals("R001", Objects.requireNonNull(response.getBody()).getError().getCode())
        );

        verify(logging).error(anyString(), eq(exception), eq("R001"), eq("/api/test"), eq("Error repo"));
    }
}
