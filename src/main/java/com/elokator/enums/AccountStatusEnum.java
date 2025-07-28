package com.elokator.enums;

public enum AccountStatusEnum {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    TEMPORARY_ACCOUNT("TEMPORARY_ACCOUNT");

    private String status;

    AccountStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
