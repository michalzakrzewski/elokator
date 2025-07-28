package com.elokator.repository;

import com.elokator.enums.TruncateSessionReasonEnum;
import com.elokator.mapper.CustomerSessionMapper;
import com.elokator.model.session.CustomerSessionHistoryModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomerSessionRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerSessionRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    private final CustomerSessionMapper customerSessionMapper;

    @SuppressWarnings("unused")
    public CustomerSessionRepository() {
        this(null);
    }

    @Autowired
    public CustomerSessionRepository(final CustomerSessionMapper customerSessionMapper) {
        this.customerSessionMapper = customerSessionMapper;
    }

    @Transactional
    public void addCustomerToSessionHistory(final String sessionId,
                                            final Integer customerId) {
        final String query = "INSERT INTO customer_session_history (session_id, customer_id, login_date) " +
                "VALUES (:sessionId, :customerId, :loginDate)";

        entityManager.createNativeQuery(query)
                .setParameter("sessionId", sessionId)
                .setParameter("customerId", customerId)
                .setParameter("loginDate", LocalDateTime.now())
                .executeUpdate();

        LOG.debug("Added customer session history for session id {}", sessionId);
    }

    @Transactional
    public void updateLogoutCustomerSessionHistory(final String sessionId, TruncateSessionReasonEnum truncateReason) {
        final String query = "UPDATE customer_session_history SET logout_date = :logoutDate, logout_reason = :logoutReason WHERE session_id = :sessionId";
        entityManager.createNativeQuery(query)
                .setParameter("sessionId", sessionId)
                .setParameter("logoutDate", LocalDateTime.now())
                .setParameter("logoutReason", truncateReason.name())
                .executeUpdate();

        LOG.debug("Updated logout date for customer session history with session id: {} with reason: {}", sessionId, truncateReason);
    }


    @SuppressWarnings("unchecked")
    public List<CustomerSessionHistoryModel> getCustomerSessionHistory(final LocalDateTime dateFrom,
                                                                       final LocalDateTime dateTo,
                                                                       final String limit,
                                                                       final String offset,
                                                                       final Integer customerId) {
        final String query = "SELECT customer_id, session_id, login_date, logout_date FROM customer_session_history " +
                "WHERE customer_id = :customerId AND login_date BETWEEN :dateFrom AND :dateTo ORDER BY login_date DESC " +
                "LIMIT :limit OFFSET :offset";

        final List<Object[]> result = entityManager.createNativeQuery(query)
                .setParameter("customerId", customerId)
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo)
                .setParameter("limit", limit)
                .setParameter("offset", offset)
                .getResultList();

        if (result.isEmpty()) {
            LOG.warn("No customer session history found for customer id {}", customerId);
            return Collections.emptyList();
        }

        final List<CustomerSessionHistoryModel> customerSessionHistoryList = new ArrayList<>();
        for (Object[] row : result) {
            customerSessionHistoryList.add(customerSessionMapper.buildResultCustomerSessionHistoryModel(row));
        }

        LOG.debug("Customer: {} session history: {}", customerId, customerSessionHistoryList);
        return customerSessionHistoryList;
    }
}
