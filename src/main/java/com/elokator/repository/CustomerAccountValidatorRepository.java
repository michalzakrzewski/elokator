package com.elokator.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerAccountValidatorRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountValidatorRepository.class);

    @PersistenceContext
    private EntityManager entityManager;


    public Boolean checkIfCustomerLoginExist(final String emailAddress) {
        final String query = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END AS emailLoginExist" +
                " from customer_account where email_address = :emailAddress";

        final Boolean isEmailAddressExist = (Boolean) entityManager.createNativeQuery(query)
                .setParameter("emailAddress", emailAddress)
                .getSingleResult();
        LOG.debug("Checked if email address: {} exist: {}?", emailAddress, isEmailAddressExist);
        return isEmailAddressExist;
    }

    public Boolean checkIfCustomerPeselNumberExist(final String peselNumber) {
        final String query = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END AS peselNumberExist " +
                "from customer_account where pesel_number = :peselNumber";

        final Boolean isPeselNumberExist = (Boolean) entityManager.createNativeQuery(query)
                .setParameter("peselNumber", peselNumber)
                .getSingleResult();
        LOG.debug("Checked if pesel number: {} exist: {}?", peselNumber, isPeselNumberExist);
        return isPeselNumberExist;
    }
}
