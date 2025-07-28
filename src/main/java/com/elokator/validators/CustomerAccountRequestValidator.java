package com.elokator.validators;

import com.elokator.dto.request.CustomerLoginRequest;
import com.elokator.dto.request.CustomerRegisterRequest;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.CustomerAccountError;
import com.elokator.tools.DateCoreTool;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Component
public class CustomerAccountRequestValidator {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountRequestValidator.class);
    private static final String PHONE_PATTERN = "^\\d{9}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d)[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{6,20}$";

    private final DateCoreTool dateCoreTool;

    @SuppressWarnings("unused")
    public CustomerAccountRequestValidator() {
        this(null);
    }

    @Autowired
    public CustomerAccountRequestValidator(final DateCoreTool dateCoreTool) {
        this.dateCoreTool = dateCoreTool;
    }

    public void validateCustomerRegisterRequest(final CustomerRegisterRequest customerRegisterRequest) throws AppCoreException {
        LOG.debug("Start validate customer register request: {}", customerRegisterRequest);

        if (customerRegisterRequest == null) {
            LOG.error("Customer register request is null");
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid register request");
        }

        if (StringUtils.isEmpty(customerRegisterRequest.getEmailAddress())) {
            LOG.error("Customer register request email address is null or empty: [{}]", customerRegisterRequest.getEmailAddress());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid email address");
        }

        if (validateEmail(customerRegisterRequest.getEmailAddress())) {
            LOG.error("Email address: {} contains illegal characters", customerRegisterRequest.getEmailAddress());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_EMAIL_CHARACTERS_ERROR, "Invalid email characters");
        }

        if (StringUtils.isEmpty(customerRegisterRequest.getFirstName())) {
            LOG.error("Customer register request first name is null or empty: [{}]", customerRegisterRequest.getFirstName());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid first name");
        }

        if (StringUtils.isEmpty(customerRegisterRequest.getLastName())) {
            LOG.error("Customer register request last name is null or empty: [{}]", customerRegisterRequest.getLastName());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid last name");
        }

        if (StringUtils.isEmpty(customerRegisterRequest.getPassword())) {
            LOG.error("Customer register request password is null or empty: [{}]", customerRegisterRequest.getPassword());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid password");
        }

        if (validatePassword(customerRegisterRequest.getPassword())) {
            LOG.error("Password: {} should contain between 6 and 20 characters, one uppercase letter, digits, and special characters", customerRegisterRequest.getPassword());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_PASSWORD_CHARACTERS_ERROR, "Invalid password characters");
        }

        if (StringUtils.isEmpty(customerRegisterRequest.getCity())) {
            LOG.error("Customer register request city is null or empty: [{}]", customerRegisterRequest.getCity());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid city");
        }

        if (StringUtils.isEmpty(customerRegisterRequest.getMobileNumber())) {
            LOG.error("Customer register request mobileNumber is null or empty: [{}]", customerRegisterRequest.getMobileNumber());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid mobile number");
        }

        if (validateMobileNumber(customerRegisterRequest.getMobileNumber())) {
            LOG.error("Mobile number: {} has illegal characters", customerRegisterRequest.getMobileNumber());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid mobile number characters");
        }

        if (customerRegisterRequest.getCountry() == null) {
            LOG.error("Customer register request country is null: [{}]", customerRegisterRequest.getCountry());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid country");
        }

        if (customerRegisterRequest.getCountryCode() == null) {
            LOG.error("Customer register request countryCode is null: [{}]", customerRegisterRequest.getCountryCode());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid country code");
        }

        if (StringUtils.isEmpty(customerRegisterRequest.getPeselNumber())) {
            LOG.error("Customer register request peselNumber is null or empty: [{}]", customerRegisterRequest.getPeselNumber());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid pesel number");
        }

        if (validatePeselNumber(customerRegisterRequest.getPeselNumber())) {
            LOG.error("Customer register request peselNumber is incorrect: [{}]", customerRegisterRequest.getPeselNumber());
            throw new AppCoreException(CustomerAccountError.INCORRECT_CUSTOMER_PESEL_NUMBER_ERROR, "Incorrect format pesel number");
        }

        if (customerRegisterRequest.getDateOfBirth() == null) {
            LOG.error("Customer register request dateOfBirth is null");
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_REGISTER_REQUEST_ERROR, "Invalid date of birth");
        }

        if (!dateCoreTool.isAdult(customerRegisterRequest.getDateOfBirth(), customerRegisterRequest.getCountryCode().getAlpha2Code())) {
            LOG.error("Customer is not adult. Date of birth: {}", customerRegisterRequest.getDateOfBirth());
            throw new AppCoreException(CustomerAccountError.CUSTOMER_IS_NOT_ADULT_ERROR, "Customer is not adult");
        }

        if (checkCorrectlyPeselNumberAndDateOfBirth(customerRegisterRequest.getPeselNumber(), customerRegisterRequest.getDateOfBirth())) {
            LOG.error("Customer register request peselNumber: {} and dateOfBirth: {} are not the same", customerRegisterRequest.getPeselNumber(), customerRegisterRequest.getDateOfBirth());
            throw new AppCoreException(CustomerAccountError.INCORRECT_CUSTOMER_PESEL_NUMBER_AND_DATE_OF_BIRTH_ERROR, "Pesel number and dateOfBirth are not the same");
        }

        LOG.debug("End validate customer register request: {}. Request correctly!", customerRegisterRequest);
    }

    private boolean checkCorrectlyPeselNumberAndDateOfBirth(final String peselNumber,
                                                            final LocalDate dateOfBirth) {
        int year = Integer.parseInt(peselNumber.substring(0, 2));
        int month = Integer.parseInt(peselNumber.substring(2, 4));
        int day = Integer.parseInt(peselNumber.substring(4, 6));

        if (month >= 1 && month <= 12) {
            year += 1900;
        } else if (month >= 21 && month <= 32) {
            year += 2000;
            month -= 20;
        } else if (month >= 81 && month <= 92) {
            year += 1800;
            month -= 80;
        } else {
            return false;
        }

        LocalDate peselDate;
        try {
            peselDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            return false;
        }
        return !peselDate.equals(dateOfBirth);
    }

    public void validateCustomerLoginRequest(final CustomerLoginRequest customerLoginRequest) throws AppCoreException {
        LOG.debug("Start validate customer login request: {}", customerLoginRequest);
        if (customerLoginRequest == null) {
            LOG.error("Customer login request is null");
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_LOGIN_REQUEST_ERROR, "Invalid login request");
        }

        if (StringUtils.isEmpty(customerLoginRequest.getEmailAddress())) {
            LOG.error("Customer login request email address is null or empty: [{}]", customerLoginRequest.getEmailAddress());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_LOGIN_ERROR, "Invalid login");
        }

        if (StringUtils.isEmpty(customerLoginRequest.getPassword())) {
            LOG.error("Customer login request password is null or empty: [{}]", customerLoginRequest.getPassword());
            throw new AppCoreException(CustomerAccountError.INVALID_CUSTOMER_PASSWORD_ERROR, "Invalid password");
        }

        LOG.debug("End validate customer login request: {}. Request correctly!", customerLoginRequest);
    }

    private boolean validatePassword(final String password) {
        LOG.debug("Start validate password: {}", password);
        return !Pattern.matches(PASSWORD_PATTERN, password);
    }

    private boolean validateEmail(final String email) {
        LOG.debug("Start validate email: {}", email);
        return !Pattern.matches(EMAIL_PATTERN, email);
    }

    private boolean validateMobileNumber(final String mobileNumber) {
        LOG.debug("Start validate mobileNumber: {}", mobileNumber);
        return !Pattern.matches(PHONE_PATTERN, mobileNumber);
    }

    private boolean validatePeselNumber(final String peselNumber) {
        LOG.debug("Start validate peselNumber: {}", peselNumber);
        if (!peselNumber.matches("\\d{11}")) {
            return true;
        }

        final int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(peselNumber.charAt(i));
            sum += digit * weights[i];
        }

        int controlNumber = 10 - (sum % 10);
        if (controlNumber == 10) {
            controlNumber = 0;
        }
        int lastDigit = Character.getNumericValue(peselNumber.charAt(10));

        return controlNumber != lastDigit;
    }
}
