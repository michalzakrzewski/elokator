package com.elokator.mapper;

import com.elokator.dto.request.AnnouncementRequest;
import com.elokator.dto.response.AnnouncementResponse;
import com.elokator.model.annoumcement.AnnouncementModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AnnouncementMapper {

    public AnnouncementModel mapAnnoucementRequestToAnnoumcementModel(final String firstName,
                                                                      final Integer customerId,
                                                                      final AnnouncementRequest announcementRequest) {
        return AnnouncementModel.builder()
                .withCustomerId(customerId)
                .withTitle(announcementRequest.getTitle())
                .withDescription(announcementRequest.getDescription())
                .withAuthor(firstName)
                .withPublicationDate(LocalDateTime.now())
                .build();
    }

    public AnnouncementResponse mapAnnouncementModelToAnnouncementResponse(final AnnouncementModel announcementModel) {
        return AnnouncementResponse.builder()
                .withAuthor(announcementModel.getAuthor())
                .withTitle(announcementModel.getTitle())
                .withDescription(announcementModel.getDescription())
                .withPublicationDate(announcementModel.getPublicationDate())
                .build();
    }
}
