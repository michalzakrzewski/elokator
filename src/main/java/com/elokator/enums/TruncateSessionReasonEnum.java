package com.elokator.enums;

public enum TruncateSessionReasonEnum {
    CUSTOMER_LOGOUT(1, "customer logout"),
    EXPIRED_SESSION(2, "expired session");

    private final int code;
    private final String description;

    TruncateSessionReasonEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static TruncateSessionReasonEnum getByCode(int code) {
        for (TruncateSessionReasonEnum truncateSessionReasonEnum : values()) {
            if (truncateSessionReasonEnum.getCode() == code) {
                return truncateSessionReasonEnum;
            }
        }
        return null;
    }
}
