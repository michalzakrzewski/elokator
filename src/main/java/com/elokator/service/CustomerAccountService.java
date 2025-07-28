package com.elokator.service;

import com.elokator.enums.TruncateSessionReasonEnum;
import com.elokator.repository.CustomerAccountRepository;
import com.elokator.dto.request.CustomerLoginRequest;
import com.elokator.dto.request.CustomerRegisterRequest;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.CustomerAccountError;
import com.elokator.model.session.CustomerData;
import com.elokator.model.session.CustomerSessionData;
import com.elokator.tools.PasswordTool;
import com.elokator.utils.AppCoreConstants;
import com.elokator.validators.CustomerAccountRequestValidator;
import com.elokator.validators.CustomerAccountValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Service
public class CustomerAccountService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountService.class);

    private final CustomerAccountRepository customerAccountRepository;
    private final CustomerSessionService customerSessionService;
    private final CustomerAccountValidator customerAccountValidator;
    private final CustomerSessionCacheService customerSessionCacheService;
    private final CustomerAccountRequestValidator customerAccountRequestValidator;

    @SuppressWarnings("unused")
    public CustomerAccountService() {
        this(null, null, null, null,
                null);
    }

    @Autowired
    public CustomerAccountService(final CustomerAccountRepository customerAccountRepository,
                                  final CustomerSessionService customerSessionService,
                                  final CustomerAccountValidator customerAccountValidator,
                                  final CustomerSessionCacheService customerSessionCacheService,
                                  final CustomerAccountRequestValidator customerAccountRequestValidator) {
        this.customerAccountRepository = customerAccountRepository;
        this.customerSessionService = customerSessionService;
        this.customerAccountValidator = customerAccountValidator;
        this.customerSessionCacheService = customerSessionCacheService;
        this.customerAccountRequestValidator = customerAccountRequestValidator;
    }

    public Integer register(final CustomerRegisterRequest customerRegisterRequest) throws AppCoreException {
        LOG.info("Starting create new account....");
        customerAccountRequestValidator.validateCustomerRegisterRequest(customerRegisterRequest);
        customerAccountValidator.checkIfCustomerRegisterRequestIsUnique(customerRegisterRequest);
        supplementCustomerRegisterRequest(customerRegisterRequest);

        final Integer customerId = customerAccountRepository.createAccount(customerRegisterRequest);
        LOG.info("Created new account with customerId: {}", customerId);
        return customerId;
    }

    public CustomerData login(final CustomerLoginRequest customerLoginRequest) throws AppCoreException {
        LOG.info("Starting customer login...");
        customerAccountRequestValidator.validateCustomerLoginRequest(customerLoginRequest);

        final String emailAddress = customerLoginRequest.getEmailAddress();
        customerAccountValidator.validateCustomerLoginIfExist(emailAddress);
        customerAccountRepository.validatePasswordForCustomer(customerLoginRequest.getPassword(), emailAddress);

        final CustomerSessionData customerSessionData = customerAccountRepository.login(customerLoginRequest);
        customerSessionCacheService.addCustomerToSession(customerSessionData);
        customerSessionService.addCustomerToSessionHistory(customerSessionData.getSessionId(), customerSessionData.getCustomerId());
        LOG.info("Customer login successful");
        return customerSessionData;
    }

    private void supplementCustomerRegisterRequest(final CustomerRegisterRequest customerRegisterRequest) throws AppCoreException {
        customerRegisterRequest.setLoginName(customerRegisterRequest.getEmailAddress());
        customerRegisterRequest.setPassword(PasswordTool.encryptPassword(customerRegisterRequest.getPassword()));
    }

    public boolean logout(final CustomerData customerData) throws AppCoreException {
        final HttpServletRequest httpServletRequest = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String sessionId = Objects.requireNonNull(httpServletRequest).getHeader(AppCoreConstants.CustomerSession.X_APP_SESSION);
        final String[] splitedSessionId = sessionId.split("\\.");
        sessionId = splitedSessionId[0];
        if (!StringUtils.equals(sessionId, customerData.getSessionId())) {
            LOG.error("Session id mismatch. CustomerData session: '{}', Header session: '{}'", customerData.getSessionId(), sessionId);
            throw new AppCoreException(CustomerAccountError.INVALID_SESSION_ERROR, "Invalid session or expired");
        }
        customerSessionCacheService.removeCustomerFromSession(sessionId);
        customerSessionService.updateCustomerSessionHistory(sessionId, customerData.getCustomerId(), TruncateSessionReasonEnum.CUSTOMER_LOGOUT);
        return true;
    }
}
