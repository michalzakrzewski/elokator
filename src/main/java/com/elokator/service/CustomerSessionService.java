package com.elokator.service;

import com.elokator.cache.provider.InfinispanCacheApp;
import com.elokator.enums.TruncateSessionReasonEnum;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.ApiError;
import com.elokator.model.session.CustomerData;
import com.elokator.model.session.CustomerSessionHistoryModel;
import com.elokator.repository.CustomerSessionRepository;
import com.elokator.tools.DateCoreTool;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerSessionService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerSessionService.class);

    private final DateCoreTool dateCoreTool;
    private final InfinispanCacheApp infinispanCacheApp;
    private final CustomerSessionRepository customerSessionRepository;

    @SuppressWarnings("unused")
    public CustomerSessionService() {
        this(null, null, null);
    }

    @Autowired
    public CustomerSessionService(final DateCoreTool dateCoreTool,
                                  final InfinispanCacheApp infinispanCacheApp,
                                  final CustomerSessionRepository customerSessionRepository) {
        this.dateCoreTool = dateCoreTool;
        this.infinispanCacheApp = infinispanCacheApp;
        this.customerSessionRepository = customerSessionRepository;
    }

    public void addCustomerToSessionHistory(final String sessionId,
                                            final Integer customerId) {
        LOG.info("Add session history for session id: {} and customer: {}", sessionId, customerId);
        customerSessionRepository.addCustomerToSessionHistory(sessionId, customerId);
    }

    public void updateCustomerSessionHistory(final String sessionId,
                                             final Integer customerId,
                                             final TruncateSessionReasonEnum truncateSessionReason) {
        LOG.info("Update session history for customer: {} with session id: {}", customerId, sessionId);
        customerSessionRepository.updateLogoutCustomerSessionHistory(sessionId, truncateSessionReason);
    }

    @SuppressWarnings("unchecked")
    public List<CustomerSessionHistoryModel> getCustomerSessionHistory(final LocalDate dateFrom,
                                                                       final LocalDate dateTo,
                                                                       final String limit,
                                                                       final String offset,
                                                                       final CustomerData customerData) throws AppCoreException {
        if (dateFrom == null || dateTo == null) {
            LOG.error("Date from: {} or date to: {} is null or empty", dateFrom, dateTo);
            throw new AppCoreException(ApiError.INVALID_DATE_PARAMS, "Invalid date params");
        }

        final Integer customerId = customerData.getCustomerId();
        final LocalDateTime formattedDateFrom = dateCoreTool.parseDateWithStartOfDay(dateFrom);
        final LocalDateTime formattedDateTo = dateCoreTool.parseDateWithStartOfDay(dateTo);
        LOG.info("Get customer session history for customer id {} with params: [dateFrom: {}, dateTo: {}, limit: {}, offset: {}]", customerId, formattedDateFrom, formattedDateTo, limit, offset);
        final String cacheKey = "CUSTOMER_SESSION_HISTORY_{0}_{1}_{2}_{3}_{4}"
                .replace("{0}", String.valueOf(customerId))
                .replace("{1}", String.valueOf(formattedDateFrom))
                .replace("{2}", String.valueOf(formattedDateTo))
                .replace("{3}", limit)
                .replace("{4}", offset);

        List<CustomerSessionHistoryModel> customerSessionHistoryList = (List<CustomerSessionHistoryModel>) infinispanCacheApp.retrieveDataFromCache(cacheKey, 60);
        if (CollectionUtils.isNotEmpty(customerSessionHistoryList)) {
            return customerSessionHistoryList;
        } else {
            try {
                customerSessionHistoryList= fetchGetCustomerSessionHistory(formattedDateFrom, formattedDateTo, limit, offset, customerId);
                if (CollectionUtils.isEmpty(customerSessionHistoryList)) {
                    LOG.warn("Not found customer session history for customer id {}", customerId);
                    return Collections.emptyList();
                }
                infinispanCacheApp.put(cacheKey, customerSessionHistoryList);
                return customerSessionHistoryList;
            } finally {
                infinispanCacheApp.endUpdate(cacheKey);
            }
        }
    }

    private List<CustomerSessionHistoryModel> fetchGetCustomerSessionHistory(final LocalDateTime dateFrom,
                                                                             final LocalDateTime dateTo,
                                                                             final String limit,
                                                                             final String offset,
                                                                             final Integer customerId) {
        return customerSessionRepository.getCustomerSessionHistory(dateFrom, dateTo, limit, offset, customerId);
    }
}
