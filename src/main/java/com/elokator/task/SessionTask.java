package com.elokator.task;

import com.elokator.enums.TruncateSessionReasonEnum;
import com.elokator.model.session.CustomerSessionData;
import com.elokator.service.CustomerSessionCacheService;
import com.elokator.service.CustomerSessionService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class SessionTask {
    private static final Logger LOG = LoggerFactory.getLogger(SessionTask.class);

    private final CustomerSessionService customerSessionService;
    private final CustomerSessionCacheService customerSessionCacheService;

    @SuppressWarnings("unused")
    public SessionTask () {
        this(null, null);
    }

    @Autowired
    public SessionTask(final CustomerSessionService customerSessionService,
                       final CustomerSessionCacheService customerSessionCacheService) {
        this.customerSessionService = customerSessionService;
        this.customerSessionCacheService = customerSessionCacheService;
    }

    @Scheduled(fixedRate = 90000, initialDelay = 0)
    public void closeInactiveCustomerSession() {
        final LocalDateTime startTime = LocalDateTime.now();
        LOG.info("Closing Inactive Customer Session Task START: {}", startTime);
        final List<CustomerSessionData> allDataFromCache = customerSessionCacheService.getAllSessions();
        if (CollectionUtils.isEmpty(allDataFromCache)) {
            LOG.warn("No Inactive Customer Session data found in cache. Task END: {}", LocalDateTime.now());
            return;
        }
        LOG.debug("Found active sessions: {}", allDataFromCache.size());
        int closeSessionCounter = 0;
        for (final CustomerSessionData customerSessionData : allDataFromCache) {
            if (Duration.between(customerSessionData.getLastActivityDate(), LocalDateTime.now()).toMinutes() >= 2) {
                final String sessionId = customerSessionData.getSessionId();
                final Integer customerId = customerSessionData.getCustomerId();
                customerSessionCacheService.removeCustomerFromSession(sessionId);
                customerSessionService.updateCustomerSessionHistory(sessionId, customerId, TruncateSessionReasonEnum.EXPIRED_SESSION);
                closeSessionCounter++;
            }
        }

        final LocalDateTime endTime = LocalDateTime.now();
        LOG.info("Closing Inactive Customer Session Task END: {}, session closed size: {}", endTime, closeSessionCounter);
    }
}
