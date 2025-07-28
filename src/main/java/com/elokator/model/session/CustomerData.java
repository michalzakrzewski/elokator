package com.elokator.model.session;

import com.elokator.enums.CountryEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface CustomerData {

    Integer getCustomerId();
    @JsonIgnore
    String getSessionId();
    String getFirstName();
    String getLastName();
    String getEmailAddress();
    String getMobileNumber();
    String getAddress();
    String getLoginName();
    String getCountry();
    String getCountryCode();
    String getCity();
    @JsonIgnore
    LocalDateTime getLastActivityDate();
}
