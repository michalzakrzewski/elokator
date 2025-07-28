package com.elokator.annotations.resolver;

import com.elokator.annotations.CustomerSession;
import com.elokator.annotations.cache.CustomerSessionCache;
import com.elokator.cache.provider.InfinispanCacheApp;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.ApiError;
import com.elokator.exceptions.errors.CustomerAccountError;
import com.elokator.model.session.CustomerSessionData;
import com.elokator.utils.AppCoreConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
public class CustomerSessionResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerSessionResolver.class);

    private final InfinispanCacheApp cacheApp;

    @SuppressWarnings("unused")
    public CustomerSessionResolver() {
        this(null);
    }

    @Autowired
    public CustomerSessionResolver(@CustomerSessionCache final InfinispanCacheApp cacheApp) {
        this.cacheApp = cacheApp;
    }

    @Override
    public boolean supportsParameter(@NonNull final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CustomerSession.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  @NonNull final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.hasParameterAnnotation(CustomerSession.class)) {
            final HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
            String sessionId = Objects.requireNonNull(httpServletRequest).getHeader(AppCoreConstants.CustomerSession.X_APP_SESSION);
            if (StringUtils.isEmpty(sessionId)) {
                LOG.error("Session Id is empty or null: {}", sessionId);
                throw new AppCoreException(CustomerAccountError.INVALID_SESSION_ERROR, "Invalid session or expired");
            }
            final String[] splitedSessionId = sessionId.split("\\.");
            sessionId = splitedSessionId[0];
            final CustomerSessionData customerSessionData = (CustomerSessionData) cacheApp.get(sessionId);
            if (customerSessionData == null) {
                LOG.error("Can not get customer by sessionId: {}", splitedSessionId[0]);
                throw new AppCoreException(CustomerAccountError.INVALID_SESSION_ERROR, "Invalid session or expired");
            }

            LOG.debug("Customer session found: {}", customerSessionData);
            LOG.info("Correctly get customer from session: {}", sessionId);
            return customerSessionData;
        }
        throw new AppCoreException(ApiError.INVALID_DATA, "Problem with annotation customer session");
    }
}
