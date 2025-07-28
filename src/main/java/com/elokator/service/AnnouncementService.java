package com.elokator.service;

import com.elokator.annotations.cache.AnnouncementCache;
import com.elokator.cache.provider.InfinispanCacheApp;
import com.elokator.dto.request.AnnouncementRequest;
import com.elokator.dto.response.AnnouncementResponse;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.AnnouncementError;
import com.elokator.exceptions.errors.ApiError;
import com.elokator.mapper.AnnouncementMapper;
import com.elokator.model.annoumcement.AnnouncementModel;
import com.elokator.model.session.CustomerData;
import com.elokator.repository.AnnouncementRepository;
import com.elokator.tools.DateCoreTool;
import com.elokator.validators.AnnouncementValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class AnnouncementService {
    private static final Logger LOG = LoggerFactory.getLogger(AnnouncementService.class);

    private final DateCoreTool dateCoreTool;
    private final AnnouncementMapper announcementMapper;
    private final InfinispanCacheApp infinispanCacheApp;
    private final AnnouncementValidator announcementValidator;
    private final AnnouncementRepository announcementRepository;

    @SuppressWarnings("unused")
    public AnnouncementService() {
        this(null, null, null, null, null);
    }

    @Autowired
    public AnnouncementService(final DateCoreTool dateCoreTool,
                               final AnnouncementMapper announcementMapper,
                               final AnnouncementValidator announcementValidator,
                               final AnnouncementRepository announcementRepository,
                               @AnnouncementCache final InfinispanCacheApp infinispanCacheApp) {
        this.dateCoreTool = dateCoreTool;
        this.announcementMapper = announcementMapper;
        this.infinispanCacheApp = infinispanCacheApp;
        this.announcementValidator = announcementValidator;
        this.announcementRepository = announcementRepository;
    }

    public boolean createAnnouncement(final CustomerData customerData,
                                     final AnnouncementRequest announcementRequest) throws AppCoreException {
        announcementValidator.validateAnnouncementRequest(announcementRequest);

        final Integer customerId = customerData.getCustomerId();
        final String firstName = customerData.getFirstName();
        final AnnouncementModel announcementModel = announcementMapper.mapAnnoucementRequestToAnnoumcementModel(firstName, customerId, announcementRequest);
        final Integer announcementId = announcementRepository.createAnnouncement(announcementModel);
        LOG.info("Created announcement {}", announcementId);
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<AnnouncementResponse> getAllPublicAnnouncements(final LocalDate dateFrom,
                                                                final LocalDate dateTo,
                                                                final String limit,
                                                                final String offset) throws AppCoreException {
        if (dateFrom == null || dateTo == null) {
            LOG.error("Date from: {} or date to: {} is null or empty", dateFrom, dateTo);
            throw new AppCoreException(ApiError.INVALID_DATE_PARAMS, "Invalid date params");
        }

        final LocalDateTime formattedDateFrom = dateCoreTool.parseDateWithStartOfDay(dateFrom);
        final LocalDateTime formattedDateTo = dateCoreTool.parseDateWithEndOfDay(dateTo);
        LOG.info("Getting public all announcements with params: [dateFrom: {}, dateTo: {}, limit: {}, offset:{}]", formattedDateFrom, formattedDateTo, limit, offset);
        final String cacheKey = "ALL_PUBLIC_ANNOUNCEMENT_{0}_{1}_{2}_{3}"
                .replace("{1}", String.valueOf(formattedDateFrom))
                .replace("{2}", String.valueOf(formattedDateTo))
                .replace("{3}", String.valueOf(limit))
                .replace("{4}", String.valueOf(offset));

        List<AnnouncementResponse> announcementResponses = (List<AnnouncementResponse>) infinispanCacheApp.retrieveDataFromCache(cacheKey, 60);
        if (CollectionUtils.isNotEmpty(announcementResponses)) {
            return announcementResponses;
        } else {
            try {
                final List<AnnouncementModel> announcementList = fetchGetAllPublicAnnouncements(limit, offset, formattedDateFrom, formattedDateTo);
                if (CollectionUtils.isEmpty(announcementList)) {
                    LOG.warn("Not found any public announcements with params: dateFrom = {}, dateTo = {}, limit = {}, offset = {}", dateFrom, dateTo, limit, offset);
                    return Collections.emptyList();
                }
                announcementResponses = announcementList.stream()
                        .map(announcementMapper::mapAnnouncementModelToAnnouncementResponse)
                        .toList();
                infinispanCacheApp.put(cacheKey, announcementResponses);
                return announcementResponses;
            } finally {
                infinispanCacheApp.endUpdate(cacheKey);
            }
        }
    }

    private List<AnnouncementModel> fetchGetAllPublicAnnouncements(final String limit,
                                                                   final String offset,
                                                                   final LocalDateTime formattedDateFrom,
                                                                   final LocalDateTime formattedDateTo) {
        return announcementRepository.getAllPublicAnnouncements(limit, offset, formattedDateFrom, formattedDateTo);
    }

    public AnnouncementResponse getAnnouncementById(final Integer announcementId,
                                                    final CustomerData customerData) throws AppCoreException {
        if (announcementId == null) {
            LOG.error("Announcement id is null");
            throw new AppCoreException(ApiError.INVALID_DATA, "invalid announcementId");
        }

        final AnnouncementModel announcement = announcementRepository.getAnnouncementById(announcementId);
        if (announcement == null) {
            LOG.warn("Announcement with id {} not found", announcementId);
            throw new AppCoreException(AnnouncementError.ANNOUNCEMENT_NOT_FOUND_ERROR, "can not find announcement");
        }

        final AnnouncementResponse announcementResponse = announcementMapper.mapAnnouncementModelToAnnouncementResponse(announcement);
        LOG.info("Found announcement {}", announcementResponse.toString());
        return announcementResponse;
    }

    public AnnouncementResponse editAnnouncement(final Integer announcementId,
                                                 final CustomerData customerData,
                                                 final AnnouncementRequest announcementRequest) {
        return null;
    }

    public boolean deleteAnnouncement(final Integer announcementId, final CustomerData customerData) {
        return false;
    }
}
