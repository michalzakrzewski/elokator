package com.elokator.validators;

import com.elokator.dto.request.AnnouncementRequest;
import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.AnnouncementError;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementValidator {
    private static final Logger LOG = LoggerFactory.getLogger(AnnouncementValidator.class);

    public void validateAnnouncementRequest(final AnnouncementRequest announcementRequest) throws AppCoreException {
        LOG.debug("Validating announcement request: {}", announcementRequest);

        if (StringUtils.isEmpty(announcementRequest.getTitle())) {
            LOG.error("Title is null or empty: [{}]", announcementRequest.getTitle());
            throw new AppCoreException(AnnouncementError.ANNOUNCEMENT_TITLE_DEOS_NOT_EXIST_ERROR, "Title does not exist");
        }

        if (StringUtils.isEmpty(announcementRequest.getDescription())) {
            LOG.error("Description is null or empty: [{}]", announcementRequest.getDescription());
            throw new AppCoreException(AnnouncementError.ANNOUNCEMENT_DESCRIPTION_DEOS_NOT_EXIST_ERROR, "Description deos not exist");
        }

        LOG.debug("Correctly validated announcement request");
    }
}
