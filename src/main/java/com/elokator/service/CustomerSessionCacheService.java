package com.elokator.service;

import com.elokator.annotations.cache.CustomerSessionCache;
import com.elokator.cache.provider.InfinispanCacheApp;
import com.elokator.model.session.CustomerSessionData;
import com.elokator.utils.AppCoreConstants;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class CustomerSessionCacheService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerSessionCacheService.class);

    private final InfinispanCacheApp cacheApp;

    @SuppressWarnings("unused")
    public CustomerSessionCacheService() {
        this(null);
    }

    @Autowired
    public CustomerSessionCacheService(@CustomerSessionCache final InfinispanCacheApp cacheApp) {
        this.cacheApp = cacheApp;
    }

    public void addCustomerToSession(final CustomerSessionData customerSessionData) {
        final HttpServletResponse response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        final String xAppSession = UUID.randomUUID().toString();
        Objects.requireNonNull(response).setHeader(AppCoreConstants.CustomerSession.X_APP_SESSION, xAppSession + ".app");
        customerSessionData.setSessionId(xAppSession);
        customerSessionData.setLastActivityDate(LocalDateTime.now());
        LOG.info("Add customer to session: {}", customerSessionData);
        cacheApp.put(xAppSession, customerSessionData);
    }

    public void removeCustomerFromSession(final String sessionId) {
        LOG.info("Remove customer from session {}", sessionId);
        cacheApp.remove(sessionId);
    }

    public void keepAlive(final String sessionId, final boolean keepAliveSession) {
        if (!keepAliveSession) {
            return;
        }

        LOG.info("Keep alive for session {}", sessionId);
        final CustomerSessionData customerSessionData = (CustomerSessionData) cacheApp.get(sessionId);
        if (Objects.isNull(customerSessionData)) {
            LOG.warn("No customer data found for session {} during keep alive session", sessionId);
            return;
        }
        customerSessionData.setLastActivityDate(LocalDateTime.now());
        cacheApp.put(sessionId, customerSessionData);
    }

    public List<CustomerSessionData> getAllSessions() {
        return cacheApp.getAllDataFromCache().stream()
                .filter(CustomerSessionData.class::isInstance)
                .map(CustomerSessionData.class::cast)
                .toList();
    }
}
