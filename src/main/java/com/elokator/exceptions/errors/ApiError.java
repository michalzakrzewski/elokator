package com.elokator.exceptions.errors;

import com.elokator.exceptions.handler.PlatformError;

public enum ApiError implements PlatformError {
    OK(200, "OK."),
    INTERNAL_EXCEPTION(500, "Internal exception"),
    INVALID_DATA(501, "Invalid data"),
    CAN_NOT_ENCRYPT_PASSWORD(502, "Can't encrypt password"),
    CAN_NOT_DECRYPT_PASSWORD(502, "Can't decrypt password"),
    INVALID_DATE_PARAMS(503, "Invalid date parameters"),
    INVALID_DATE_FORMAT(504, "Invalid date format"),
    SERIALIZATION_ERROR(505, "Serialization error"),
    DESERIALIZATION_ERROR(506, "Deserialization error")
    ;

    private final int errorCode;
    private final String errorDescription;

    ApiError(int errorCode, String errorDescription) {
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
