package com.elokator.model.annoumcement;

import com.elokator.enums.CustomerRoleEnum;

import java.time.LocalDateTime;

public class AnnouncementModel {
    private Integer announcementId;
    private String title;
    private String description;
    private String author;
    private Integer customerId;
    private LocalDateTime publicationDate;
    private CustomerRoleEnum customerRole;

    public AnnouncementModel() {
    }

    public AnnouncementModel(final Builder builder) {
        this.announcementId = builder.announcementId;
        this.title = builder.title;
        this.description = builder.description;
        this.author = builder.author;
        this.customerId = builder.customerId;
        this.publicationDate = builder.publicationDate;
        this.customerRole = builder.customerRole;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(final Integer announcementId) {
        this.announcementId = announcementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(final LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public CustomerRoleEnum getCustomerRole() {
        return customerRole;
    }

    public void setCustomerRole(final CustomerRoleEnum customerRole) {
        this.customerRole = customerRole;
    }

    public static class Builder {
        private Integer announcementId;
        private String title;
        private String description;
        private String author;
        private Integer customerId;
        private LocalDateTime publicationDate;
        private CustomerRoleEnum customerRole;

        public Builder withAnnouncementId(final Integer announcementId) {
            this.announcementId = announcementId;
            return this;
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withAuthor(final String author) {
            this.author = author;
            return this;
        }

        public Builder withCustomerId(final Integer customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withPublicationDate(final LocalDateTime publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public Builder withCustomerRole(final CustomerRoleEnum customerRole) {
            this.customerRole = customerRole;
            return this;
        }

        public AnnouncementModel build() {
            return new AnnouncementModel(this);
        }
    }
}
