package com.elokator.cache.provider;

import com.elokator.exceptions.AppCoreException;

import java.util.List;

public interface InfinispanCacheApp {
    Object get(final String key);
    void remove(final String key);
    void endUpdate(final String key);
    void put(final String key, final Object value);
    Object retrieveDataFromCache(final String cacheKey, final int secondsValid) throws AppCoreException;
    List<Object> getAllDataFromCache();

}
