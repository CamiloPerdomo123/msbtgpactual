package com.ms.mspactual.infrastructurecross.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorInfo {

    private final int status;
    private final String message;
    private final String code;
    private final String path;
}
