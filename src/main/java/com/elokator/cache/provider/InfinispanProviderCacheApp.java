package com.elokator.cache.provider;

import com.elokator.cache.config.InfinispanObjectCache;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InfinispanProviderCacheApp implements InfinispanCacheApp {
    private static final Logger LOG = LoggerFactory.getLogger(InfinispanProviderCacheApp.class);

    private final InfinispanObjectCache cache;

    public InfinispanProviderCacheApp(final String cacheName,
                                      final long maxSize,
                                      final long duration,
                                      final TimeUnit timeUnit) {
        this.cache = new InfinispanObjectCache(cacheName, maxSize, duration, timeUnit);
    }

    @Override
    public Object retrieveDataFromCache(String cacheKey, int secondsValid) throws AppCoreException {
        try {
            if (cache.isInCache(cacheKey, secondsValid)) {
                return get(cacheKey);
            } else if (cache.isUpdatingMarkIfNo(cacheKey)) {
                return get(cacheKey);
            }
            return null;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new AppCoreException(ApiError.INTERNAL_EXCEPTION, "Can not retrieve response from cache");
        }
    }

    @Override
    public void put(final String key, final Object value) {
        cache.put(key, value);
    }

    @Override
    public void remove(final String key) {
        cache.remove(key);
    }

    @Override
    public Object get(final String key) {
        return cache.get(key);
    }

    @Override
    public void endUpdate(final String key) {
        cache.endUpdate(key);
    }

    @Override
    public List<Object> getAllDataFromCache() {
        return cache.getAllDataFromCache();
    }
}
