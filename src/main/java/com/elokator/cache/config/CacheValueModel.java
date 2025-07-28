package com.elokator.cache.config;

import java.io.Serializable;

public class CacheValueModel implements Serializable {
    private final Object value;
    private final long timestamp;

    public CacheValueModel(Object value) {
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

    public Object getValue() {
        return value;
    }

    public boolean isValueValid(int secondsValid) {
        long now = System.currentTimeMillis();
        return (now - timestamp) < (secondsValid * 1000L);
    }
}
