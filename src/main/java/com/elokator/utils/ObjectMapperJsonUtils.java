package com.elokator.utils;

import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.ApiError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ObjectMapperJsonUtils implements JsonUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ObjectMapperJsonUtils.class);

    @Override
    public <T> String toJson(final T value) throws AppCoreException {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOG.debug("Serializing object: {} to JSON", value);
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOG.error("Caught JsonProcessingException during serialization object: {}", value, e);
            throw new AppCoreException(ApiError.SERIALIZATION_ERROR, "Json serialization error");
        }
    }

    @Override
    public <T> T fromJson(final String json, final Class<T> clazz) throws AppCoreException {
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            LOG.debug("Deserializing object: {} from JSON to class: {}", json, clazz);
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            LOG.error("Caught IOException during deserialization object: {} to class: {}", json, clazz, e);
            throw new AppCoreException(ApiError.DESERIALIZATION_ERROR, "Json deserialization error");
        }
    }
}
