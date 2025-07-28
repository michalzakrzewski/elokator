package com.elokator.validators;

import com.elokator.repository.CustomerAccountValidatorRepository;
import com.elokator.dto.request.CustomerRegisterRequest;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.CustomerAccountError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerAccountValidator {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountValidator.class);

    private final CustomerAccountValidatorRepository customerAccountValidatorRepository;

    @SuppressWarnings("unused")
    public CustomerAccountValidator() {
        this(null);
    }

    @Autowired
    public CustomerAccountValidator(final CustomerAccountValidatorRepository customerAccountValidatorRepository) {
        this.customerAccountValidatorRepository = customerAccountValidatorRepository;
    }

    public void checkIfCustomerRegisterRequestIsUnique(final CustomerRegisterRequest customerRegisterRequest) throws AppCoreException {
        final String emailAddress = customerRegisterRequest.getEmailAddress();
        LOG.debug("Checking login: {} if exist", emailAddress);
        final Boolean ifCustomerLoginExist = customerAccountValidatorRepository.checkIfCustomerLoginExist(emailAddress);
        if (ifCustomerLoginExist) {
            LOG.error("Customer account with email address {} exist", emailAddress);
            throw new AppCoreException(CustomerAccountError.CUSTOMER_ACCOUNT_DEOS_NOT_EXIST_ERROR, "Customer login (email) deos not exist");
        }
        LOG.debug("Customer account with email address {} is valid", emailAddress);
        final String peselNumber = customerRegisterRequest.getPeselNumber();
        validateCustomerPeselIfExist(peselNumber);
    }

    public void validateCustomerLoginIfExist(final String emailAddress) throws AppCoreException {
        LOG.debug("Checking login: {} if exist", emailAddress);
        final Boolean ifCustomerLoginExist = customerAccountValidatorRepository.checkIfCustomerLoginExist(emailAddress);
        if (!ifCustomerLoginExist) {
            LOG.error("Customer account with email address {} deos not exist", emailAddress);
            throw new AppCoreException(CustomerAccountError.CUSTOMER_ACCOUNT_DEOS_NOT_EXIST_ERROR, "Customer login (email) deos not exist");
        }
        LOG.debug("Customer account with email address {} is valid", emailAddress);
    }

    private void validateCustomerPeselIfExist(final String peselNumber) throws AppCoreException {
        LOG.debug("Checking if customer pesel number exist: {}", peselNumber);
        final Boolean ifPeselNumberExist = customerAccountValidatorRepository.checkIfCustomerPeselNumberExist(peselNumber);
        if (ifPeselNumberExist) {
            LOG.error("Customer account with pesel number {} already exist", peselNumber);
            throw new AppCoreException(CustomerAccountError.PESEL_NUMBER_ALREADY_EXIST_ERROR, "Customer pesel exist");
        }
        LOG.debug("Customer account with pesel number {} is valid", peselNumber);
    }
}
