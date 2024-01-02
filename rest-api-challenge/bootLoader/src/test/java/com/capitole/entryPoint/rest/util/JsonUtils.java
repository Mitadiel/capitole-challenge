package com.capitole.entryPoint.rest.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static byte[] convertObjectToJsonBytes(Object object) throws Exception {
        return objectMapper.writeValueAsBytes(object);
    }
}
