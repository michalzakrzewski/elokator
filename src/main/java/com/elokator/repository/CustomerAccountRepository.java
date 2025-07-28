package com.elokator.repository;

import com.elokator.dto.request.CustomerLoginRequest;
import com.elokator.dto.request.CustomerRegisterRequest;
import com.elokator.enums.AccountStatusEnum;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.CustomerAccountError;
import com.elokator.mapper.CustomerAccountMapper;
import com.elokator.model.session.CustomerSessionData;
import com.elokator.tools.PasswordTool;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public class CustomerAccountRepository {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    private final CustomerAccountMapper customerAccountMapper;

    @SuppressWarnings("unused")
    public CustomerAccountRepository() {
        this(null);
    }

    @Autowired
    public CustomerAccountRepository(final CustomerAccountMapper customerAccountMapper) {
        this.customerAccountMapper = customerAccountMapper;
    }

    @Transactional
    public Integer createAccount(final CustomerRegisterRequest customerRegisterRequest) {
        final String query = "INSERT INTO customer_account(first_name, last_name, login_name, email_address, mobile_number, country," +
                " country_code, city, address, password, account_status, date_of_birth, reg_date, pesel_number)" +
                " values(:firstName, :lastName, :loginName, :emailAddress, :mobileNumber," +
                " :country, :countryCode, :city, :address, :password, :accountStatus, :dateOfBirth, :registrationDate, :peselNumber);";
        entityManager.createNativeQuery(query)
                .setParameter("firstName", customerRegisterRequest.getFirstName())
                .setParameter("lastName", customerRegisterRequest.getLastName())
                .setParameter("loginName", customerRegisterRequest.getLoginName())
                .setParameter("emailAddress", customerRegisterRequest.getEmailAddress())
                .setParameter("mobileNumber", customerRegisterRequest.getMobileNumber())
                .setParameter("country", customerRegisterRequest.getCountry().getFullName())
                .setParameter("countryCode", customerRegisterRequest.getCountryCode().getAlpha2Code())
                .setParameter("city", customerRegisterRequest.getCity())
                .setParameter("address", customerRegisterRequest.getAddress())
                .setParameter("password", customerRegisterRequest.getPassword())
                .setParameter("accountStatus", AccountStatusEnum.TEMPORARY_ACCOUNT.getStatus())
                .setParameter("dateOfBirth", customerRegisterRequest.getDateOfBirth())
                .setParameter("registrationDate", LocalDateTime.now())
                .setParameter("peselNumber", customerRegisterRequest.getPeselNumber())
                .executeUpdate();
        final String queryGetCustomerId = "SELECT customer_id FROM customer_account ORDER BY customer_id DESC LIMIT 1;";
        final Integer customerId = (Integer) entityManager.createNativeQuery(queryGetCustomerId)
                        .getSingleResult();

        LOG.debug("Created account with customer id {}", customerId);
        return customerId;
    }

    public CustomerSessionData login(final CustomerLoginRequest customerLoginRequest) {
        final String emailAddress = customerLoginRequest.getEmailAddress();
        final String query = "SELECT customer_id, first_name, last_name, email_address, mobile_number, " +
                "address, city, country_code, country from customer_account where email_address = :emailAddress;";

        final Object[] result = (Object[]) entityManager.createNativeQuery(query)
                .setParameter("emailAddress", emailAddress)
                .getSingleResult();
        final CustomerSessionData customerSessionData = customerAccountMapper.buildCustomerSessionData(result);
        LOG.debug("Login customer: {}", customerSessionData);
        return customerSessionData;
    }

    public void validatePasswordForCustomer(final String password,
                                            final String emailAddress) throws AppCoreException {
        final String query = "SELECT password from customer_account where email_address = :emailAddres;";
        final String customerPassword = (String) entityManager.createNativeQuery(query)
                .setParameter("emailAddres", emailAddress)
                .getSingleResult();
        final String decryptPassword = PasswordTool.decryptPassword(customerPassword);
        if (!password.equals(decryptPassword)) {
            LOG.error("Invalid password for account {}", emailAddress);
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_PASSWORD_ERROR, "Invalid password");
        }
    }
}
