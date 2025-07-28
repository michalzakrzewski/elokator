package com.elokator.exceptions.errors;


import com.elokator.exceptions.handler.PlatformError;

public enum CustomerAccountError implements PlatformError {
    //Session error
    INVALID_SESSION_ERROR(418, "Invalid or expired session"),

    //Login request
    INVALID_CUSTOMER_LOGIN_REQUEST_ERROR(600, "Invalid customer login request"),
    INVALID_CUSTOMER_LOGIN_ERROR(601, "Invalid customer login"),
    INVALID_CUSTOMER_PASSWORD_ERROR(602, "Invalid customer password"),

    //Register request
    INVALID_CUSTOMER_REGISTER_REQUEST_ERROR(700, "Invalid customer register request"),
    INVALID_CUSTOMER_PASSWORD_CHARACTERS_ERROR(701, "Invalid customer password characters"),
    INVALID_CUSTOMER_EMAIL_CHARACTERS_ERROR(702, "Invalid customer email characters"),
    INCORRECT_CUSTOMER_PESEL_NUMBER_ERROR(703, "Incorrect customer pesel number"),
    INCORRECT_CUSTOMER_PESEL_NUMBER_AND_DATE_OF_BIRTH_ERROR(704, "Incorrect customer pesel number and date"),
    LOGIN_NAME_ALREADY_EXISTS_ERROR(705, "Customer login already exists"),
    PESEL_NUMBER_ALREADY_EXIST_ERROR(706, "Customer pesel number already exists"),
    CUSTOMER_ACCOUNT_DEOS_NOT_EXIST_ERROR(707, "Customer account deos not exists"),
    CUSTOMER_IS_NOT_ADULT_ERROR(708, "Customer is not adult"),
    ;
    private final int errorCode;
    private final String errorDescription;

    CustomerAccountError(int errorCode, String errorDescription) {
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
