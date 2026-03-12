package com.ms.mspactual.infrastructurecross.metricas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {

    private static volatile JsonUtil instance;
    private final ObjectMapper objectMapper;

    private JsonUtil() {
        this.objectMapper = new ObjectMapper();
    }

    public static JsonUtil getInstance() {
        if (instance == null) {
            synchronized (JsonUtil.class) {
                if (instance == null) {
                    instance = new JsonUtil();
                }
            }
        }
        return instance;
    }

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "Error serializando objeto";
        }
    }
}

