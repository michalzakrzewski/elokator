package com.elokator.service;

import com.elokator.model.session.CustomerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerDataService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerDataService.class);

    private final CustomerSessionCacheService customerSessionCacheService;

    @SuppressWarnings("unused")
    public CustomerDataService() {
        this(null);
    }

    @Autowired
    public CustomerDataService(final CustomerSessionCacheService customerSessionCacheService) {
        this.customerSessionCacheService = customerSessionCacheService;
    }

    public CustomerData getCustomerData(final boolean keepAliveSession,
                                        final CustomerData customerData) {
        final Integer customerId = customerData.getCustomerId();
        LOG.debug("Getting customer data for customer id {}", customerId);

        customerSessionCacheService.keepAlive(customerData.getSessionId(), keepAliveSession);
        return customerData;
    }
}
