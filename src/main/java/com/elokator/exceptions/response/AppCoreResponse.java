package com.elokator.exceptions.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppCoreResponse {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public AppCoreResponse() {
    }

    public AppCoreResponse(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public AppCoreResponse(Integer code, String description, Object data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
