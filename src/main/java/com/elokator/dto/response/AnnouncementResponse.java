package com.elokator.dto.response;

import java.time.LocalDateTime;

public class AnnouncementResponse {
    private String title;
    private String description;
    private String author;
    private LocalDateTime publicationDate;

    public AnnouncementResponse() {

    }

    public AnnouncementResponse(final Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.author = builder.author;
        this.publicationDate = builder.publicationDate;
    }

    public static Builder builder() {
        return new Builder();
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

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(final LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public static class Builder {
        private String title;
        private String description;
        private String author;
        private LocalDateTime publicationDate;

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

        public Builder withPublicationDate(final LocalDateTime publicationDate) {
            this.publicationDate = publicationDate;
            return this;
        }

        public AnnouncementResponse build() {
            return new AnnouncementResponse(this);
        }
    }

    @Override
    public String toString() {
        return "AnnouncementResponse{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
