package com.elokator.model.annoumcement;

import com.elokator.enums.CustomerRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class AnnouncementModel {
    private Integer announcementId;
    private String title;
    private String description;
    private String author;
    private Integer customerId;
    private LocalDateTime publicationDate;
    private CustomerRoleEnum customerRole;
}
