package com.elokator.tools;

import com.elokator.exceptions.AppCoreException;
import com.elokator.exceptions.errors.ApiError;
import com.elokator.exceptions.response.AppCoreResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@Component
@RestControllerAdvice
public class ApiCoreTools {

    @ExceptionHandler(AppCoreException.class)
    public AppCoreResponse buildOkResponse(Object data) {
        AppCoreResponse appCoreResponse;

        if (data == null) {
            data = new ArrayList<>();
        }

        if (data instanceof AppCoreException) {
            appCoreResponse = new AppCoreResponse(((AppCoreException) data).getError().getErrorCode(), ((AppCoreException) data).getDescription());
            return appCoreResponse;
        }

        appCoreResponse = new AppCoreResponse(ApiError.OK.getErrorCode(), ApiError.OK.getErrorDescription(), data);
        return appCoreResponse;
    }
}