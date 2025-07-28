package com.elokator.dto.request;

public class AnnouncementRequest {
    private String title;
    private String description;

    public AnnouncementRequest() {
    }

    public AnnouncementRequest(final String title, final String description) {
        this.title = title;
        this.description = description;
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

    @Override
    public String toString() {
        return "AnnoumcementRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
