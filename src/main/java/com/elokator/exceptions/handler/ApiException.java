package com.elokator.exceptions.handler;

import org.apache.commons.lang3.StringUtils;

public class ApiException extends Exception {

    private PlatformError error;
    private String errorDescription;
    private String additionalDescription;

    public ApiException(PlatformError error) {
        super();
        this.error = error;
        this.errorDescription = error.getErrorDescription();
    }

    public ApiException(PlatformError error, String additionalInfo) {
        super();
        this.error = error;
        this.errorDescription = error.getErrorDescription() + " (" + additionalInfo + ")";
        this.additionalDescription = additionalInfo;
    }

    public ApiException(PlatformError error, Throwable cause) {
        super(cause);
        this.error = error;
        this.errorDescription = error.getErrorDescription();
    }

    public ApiException(PlatformError error, String additionalInfo, Throwable cause) {
        super(cause);
        this.error = error;
        this.errorDescription = getErrorDescription() + " (" + additionalInfo + ")";
        this.additionalDescription = additionalInfo;
    }

    public PlatformError getError() {
        return error;
    }

    public void setError(PlatformError error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getAdditionalDescription() {
        return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
        this.additionalDescription = additionalDescription;
    }

    public String getDescription() {
        String description = "";
        if (errorDescription != null && !StringUtils.isEmpty(errorDescription)) {
            description += errorDescription;
        }
        if (additionalDescription != null && !StringUtils.isEmpty(additionalDescription) && !description.contains(additionalDescription)) {
            description += StringUtils.isEmpty(description)
                    ? additionalDescription
                    : " [" + additionalDescription + "]";
        }
        if (StringUtils.isEmpty(description)) {
            description = error == null
                    ? ""
                    : error.getErrorDescription();
        }
        return description;
    }
}
