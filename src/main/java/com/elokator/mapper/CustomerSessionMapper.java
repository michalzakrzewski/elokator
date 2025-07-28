package com.elokator.mapper;

import com.elokator.model.session.CustomerSessionHistoryModel;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class CustomerSessionMapper {

    public CustomerSessionMapper() {
    }

    public CustomerSessionHistoryModel buildResultCustomerSessionHistoryModel(final Object[] result) {
        return CustomerSessionHistoryModel.builder()
                .withCustomerId((Integer) result[0])
                .withSessionId((String) result[1])
                .withLoginDate(((Timestamp) result[2]).toLocalDateTime())
                .withLogoutDate(result[3] != null ? ((Timestamp) result[3]).toLocalDateTime() : null)
                .build();
    }

    public CustomerSessionHistoryModel buildCustomerSessionHistoryModel(final String sessionId,
                                                                        final Integer customerId,
                                                                        final LocalDateTime loginDate,
                                                                        final LocalDateTime logoutDate) {
        return null;
    }
}
