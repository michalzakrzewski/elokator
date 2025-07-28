package com.elokator.exceptions.errors;

import com.elokator.exceptions.handler.PlatformError;

public enum AnnouncementError implements PlatformError {
    ANNOUNCEMENT_TITLE_DEOS_NOT_EXIST_ERROR(800, "Announcement title deos not exist"),
    ANNOUNCEMENT_DESCRIPTION_DEOS_NOT_EXIST_ERROR(801, "Announcement description deos not exist"),
    ANNOUNCEMENT_NOT_FOUND_ERROR(802, "Announcement not found");

    private final int errorCode;
    private final String errorDescription;

    AnnouncementError(final int errorCode, final String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }


    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorDescription() {
        return errorDescription;
    }
}
