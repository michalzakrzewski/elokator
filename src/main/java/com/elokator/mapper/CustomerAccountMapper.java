package com.elokator.mapper;

import com.elokator.model.session.CustomerSessionData;
import org.springframework.stereotype.Component;

@Component
public class CustomerAccountMapper {

    public CustomerAccountMapper() {
    }

    public CustomerSessionData buildCustomerSessionData(final Object[] result) {
        return CustomerSessionData.builder()
                .withCustomerId((Integer) result[0])
                .withFirstName((String) result[1])
                .withLastName((String) result[2])
                .withEmailAddress((String) result[3])
                .withMobileNumber((String) result[4])
                .withAddress((String) result[5])
                .withCity((String) result[6])
                .withCountryCode((String) result[7])
                .withCountry((String) result[8])
                .build();
    }
}
