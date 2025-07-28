package com.elokator.cache.config;

import com.elokator.annotations.cache.AnnouncementCache;
import com.elokator.annotations.cache.CustomerSessionCache;
import com.elokator.cache.provider.InfinispanProviderCacheApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
public class InfinispanCacheConfig {
    private static final Logger LOG = LoggerFactory.getLogger(InfinispanCacheConfig.class);

    @Bean
    @Primary
    public InfinispanProviderCacheApp provideInfinispanDefaultCache() {
        LOG.info("Init default infinispan cache");
        return new InfinispanProviderCacheApp("default", 20000, 5, TimeUnit.MINUTES);
    }

    @Bean
    @CustomerSessionCache
    public InfinispanProviderCacheApp provideInfinispanCustomerCache() {
        LOG.info("Init customer session infinispan cache");
        return new InfinispanProviderCacheApp("customer_session", 30000, 15, TimeUnit.MINUTES);
    }

    @Bean
    @AnnouncementCache
    public InfinispanProviderCacheApp provideInfinispanAnnouncementCache() {
        LOG.info("Init announcement infinispan cache");
        return new InfinispanProviderCacheApp("announcement", 30000, 3, TimeUnit.MINUTES);
    }
}
