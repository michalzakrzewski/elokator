package com.elokator.controllers;

import com.elokator.annotations.CustomerSession;
import com.elokator.dto.request.AnnouncementRequest;
import com.elokator.dto.response.AnnouncementResponse;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.response.AppCoreResponse;
import com.elokator.model.session.CustomerData;
import com.elokator.service.AnnouncementService;
import com.elokator.tools.ApiCoreTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/announcement")
public class AnnouncementController {

    private final ApiCoreTools apiCoreTools;
    private final AnnouncementService announcementService;

    @SuppressWarnings("unused")
    public AnnouncementController() {
        this(null, null);
    }

    @Autowired
    public AnnouncementController(final ApiCoreTools apiCoreTools,
                                  final AnnouncementService announcementService) {
        this.apiCoreTools = apiCoreTools;
        this.announcementService = announcementService;
    }

    @PostMapping(path = "/create")
    public AppCoreResponse createAnnouncement(@CustomerSession final CustomerData customerData,
                                              @RequestBody final AnnouncementRequest announcementRequest) throws AppCoreException {
        final boolean isCreated = announcementService.createAnnouncement(customerData, announcementRequest);
        return apiCoreTools.buildOkResponse(isCreated);
    }

    @GetMapping(path = "/public/list/{dateFrom}/{dateTo}/{limit}/{offset}")
    public AppCoreResponse getAllPublicAnnouncements(@PathVariable("dateFrom") final LocalDate dateFrom,
                                                     @PathVariable("dateTo") final LocalDate dateTo,
                                                     @PathVariable("limit") @DefaultValue("50") final String limit,
                                                     @PathVariable("offset") @DefaultValue("0") final String offset) throws AppCoreException {
        final List<AnnouncementResponse> publicAnnouncementList = announcementService.getAllPublicAnnouncements(dateFrom, dateTo, limit, offset);
        return apiCoreTools.buildOkResponse(publicAnnouncementList);
    }

    @GetMapping(path = "/single/{announcementId}")
    public AppCoreResponse getAnnouncement(@CustomerSession final CustomerData customerData,
                                           @PathVariable("announcementId") final Integer announcementId) throws AppCoreException {
        final AnnouncementResponse announcement = announcementService.getAnnouncementById(announcementId, customerData);
        return apiCoreTools.buildOkResponse(announcement);
    }

    @PutMapping(path = "/edit/{announcementId}")
    public AppCoreResponse editAnnouncement(@CustomerSession final CustomerData customerData,
                                            @RequestBody final AnnouncementRequest announcementRequest,
                                            @PathVariable("announcementId") final Integer announcementId) {
        final AnnouncementResponse editecAnnouncement = announcementService.editAnnouncement(announcementId, customerData, announcementRequest);
        return apiCoreTools.buildOkResponse(editecAnnouncement);
    }

    @DeleteMapping(path = "/delete/{announcementId}")
    public AppCoreResponse deleteAnnouncement(@CustomerSession final CustomerData customerData,
                                              @PathVariable("announcementId") final Integer announcementId) {
        final boolean isDeleted = announcementService.deleteAnnouncement(announcementId, customerData);
        return apiCoreTools.buildOkResponse(isDeleted);
    }
}
