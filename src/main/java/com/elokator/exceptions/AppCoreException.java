package com.elokator.exceptions;


import com.elokator.exceptions.handler.ApiException;
import com.elokator.exceptions.handler.PlatformError;

public class AppCoreException extends ApiException {

    private Object data;
    private String messageKey;
    private Throwable throwable;

    public AppCoreException(PlatformError error) {
        super(error);
    }

    public AppCoreException(PlatformError error, String additionalInfo) {
        super(error, additionalInfo);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String getMessage() {
        return getDescription();
    }
}
