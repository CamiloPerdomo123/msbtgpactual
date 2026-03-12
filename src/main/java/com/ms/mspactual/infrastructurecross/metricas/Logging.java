package com.ms.mspactual.infrastructurecross.metricas;

import com.ms.mspactual.application.port.interactor.ILogging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Logging implements ILogging {

    private final Logger log;

    public Logging() {
        this.log = LoggerFactory.getLogger("ApplicationLogger");
    }

    @Override
    public void info(String message, Object... params) {
        log.info(message, params);
    }

    @Override
    public void error(String message, Throwable ex, Object... params) {
        log.error(message, ex, params);
    }

    @Override
    public void debug(String message, Object... params) {
        log.debug(message, params);
    }

    @Override
    public void warn(String message, Object... params) {
        log.warn(message, params);
    }
}