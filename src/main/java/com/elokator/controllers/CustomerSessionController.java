package com.elokator.controllers;

import com.elokator.annotations.CustomerSession;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.response.AppCoreResponse;
import com.elokator.model.session.CustomerData;
import com.elokator.model.session.CustomerSessionHistoryModel;
import com.elokator.service.CustomerSessionService;
import com.elokator.tools.ApiCoreTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerSessionController {

    private final ApiCoreTools apiCoreTools;
    private final CustomerSessionService customerSessionService;

    @SuppressWarnings("unused")
    public CustomerSessionController() {
        this(null, null);
    }

    @Autowired
    public CustomerSessionController(final ApiCoreTools apiCoreTools,
                                     final CustomerSessionService customerSessionService) {
        this.apiCoreTools = apiCoreTools;
        this.customerSessionService = customerSessionService;
    }

    @GetMapping(path = "/session/history/{dateFrom}/{dateTo}/{limit}/{offset}")
    public AppCoreResponse getCustomerSessionHistory(@PathVariable("dateFrom") final LocalDate dateFrom,
                                                     @PathVariable("dateTo") final LocalDate dateTo,
                                                     @PathVariable("limit") @DefaultValue("50") final String limit,
                                                     @PathVariable("offset") @DefaultValue("0") final String offset,
                                                     @CustomerSession final CustomerData customerData) throws AppCoreException {
        final List<CustomerSessionHistoryModel> customerSessionHistory = customerSessionService.getCustomerSessionHistory(dateFrom, dateTo, limit, offset, customerData);
        return apiCoreTools.buildOkResponse(customerSessionHistory);
    }
}
