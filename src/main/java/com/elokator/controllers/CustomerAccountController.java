package com.elokator.controllers;

import com.elokator.annotations.CustomerSession;
import com.elokator.dto.request.CustomerLoginRequest;
import com.elokator.dto.request.CustomerRegisterRequest;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.response.AppCoreResponse;
import com.elokator.model.session.CustomerData;
import com.elokator.service.CustomerAccountService;
import com.elokator.tools.ApiCoreTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
public class CustomerAccountController {

    private final ApiCoreTools apiCoreTools;
    private final CustomerAccountService customerAccountService;

    @SuppressWarnings("unused")
    public CustomerAccountController() {
        this(null, null);
    }

    @Autowired
    public CustomerAccountController(final ApiCoreTools apiCoreTools,
                                     final CustomerAccountService customerAccountService) {
        this.apiCoreTools = apiCoreTools;
        this.customerAccountService = customerAccountService;
    }

    @PostMapping(path = "/register")
    public AppCoreResponse register(@RequestBody final CustomerRegisterRequest customerRegisterRequest) throws AppCoreException {
        final Integer customerId = customerAccountService.register(customerRegisterRequest);
        return apiCoreTools.buildOkResponse(customerId);
    }

    @PostMapping(path = "/login")
    public AppCoreResponse login(@RequestBody final CustomerLoginRequest customerLoginRequest) throws AppCoreException {
        final CustomerData customerData = customerAccountService.login(customerLoginRequest);
        return apiCoreTools.buildOkResponse(customerData);
    }

    @DeleteMapping(path = "/logout")
    public AppCoreResponse logout(@CustomerSession final CustomerData customerData) throws AppCoreException {
        final boolean isLogout = customerAccountService.logout(customerData);
        return apiCoreTools.buildOkResponse(isLogout);
    }
}
