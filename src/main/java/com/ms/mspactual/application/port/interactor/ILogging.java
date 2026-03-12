package com.ms.mspactual.application.port.interactor;

public interface ILogging {

    void info(String message, Object... params);

    void error(String message, Throwable ex, Object... params);

    void debug(String message, Object... params);

    void warn(String message, Object... params);
}