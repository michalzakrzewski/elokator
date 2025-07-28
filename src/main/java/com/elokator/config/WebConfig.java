package com.elokator.config;

import com.elokator.annotations.resolver.CustomerSessionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CustomerSessionResolver customerSessionResolver;

    @SuppressWarnings("unused")
    public WebConfig() {
        this(null);
    }

    @Autowired
    public WebConfig(final CustomerSessionResolver customerSessionResolver) {
        this.customerSessionResolver = customerSessionResolver;
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(customerSessionResolver);
    }
}
