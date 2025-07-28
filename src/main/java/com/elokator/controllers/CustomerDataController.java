package com.elokator.controllers;

import com.elokator.annotations.CustomerSession;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.response.AppCoreResponse;
import com.elokator.model.session.CustomerData;
import com.elokator.service.CustomerDataService;
import com.elokator.tools.ApiCoreTools;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerDataController {

    private final ApiCoreTools apiCoreTools;
    private final CustomerDataService customerDataService;

    @SuppressWarnings("unused")
    public CustomerDataController() {
        this(null, null);
    }

    @Autowired
    public CustomerDataController(final ApiCoreTools apiCoreTools,
                                  final CustomerDataService customerDataService) {
        this.apiCoreTools = apiCoreTools;
        this.customerDataService = customerDataService;
    }

    @GetMapping(path = "/data")
    public AppCoreResponse getCustomerData(@RequestParam(value = "keepAliveSession", defaultValue = "false") final boolean keepAliveSession,
                                           final @CustomerSession CustomerData customerData) {
        final CustomerData data = customerDataService.getCustomerData(keepAliveSession, customerData);
        return apiCoreTools.buildOkResponse(data);
    }
}
