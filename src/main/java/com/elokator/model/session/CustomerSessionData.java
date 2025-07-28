package com.elokator.model.session;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerSessionData implements CustomerData {
    private Integer customerId;
    private String sessionId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
    private String address;
    private String city;
    private String countryCode;
    private String country;
    private LocalDateTime lastActivityDate;

    public CustomerSessionData() {
    }

    public CustomerSessionData(final Builder builder) {
        this.customerId = builder.customerId;
        this.sessionId = builder.sessionId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.mobileNumber = builder.mobileNumber;
        this.address = builder.address;
        this.city = builder.city;
        this.countryCode = builder.countryCode;
        this.country = builder.country;
        this.lastActivityDate = builder.lastActivityDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getLoginName() {
        return emailAddress;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public LocalDateTime getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(final LocalDateTime lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public static class Builder {
        private Integer customerId;
        private String sessionId;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String mobileNumber;
        private String address;
        private String city;
        private String countryCode;
        private String country;
        private LocalDateTime lastActivityDate;

        public Builder withCustomerId(final Integer customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withSessionId(final String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder withFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmailAddress(final String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withMobileNumber(final String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Builder withAddress(final String address) {
            this.address = address;
            return this;
        }

        public Builder withCity(final String city) {
            this.city = city;
            return this;
        }

        public Builder withCountryCode(final String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder withCountry(final String country) {
            this.country = country;
            return this;
        }

        public Builder withLastActivityDate(final LocalDateTime lastActivityDate) {
            this.lastActivityDate = lastActivityDate;
            return this;
        }

        public CustomerSessionData build() {
            return new CustomerSessionData(this);
        }
    }

    @Override
    public String toString() {
        return "CustomerSessionData{" +
                "customerId=" + customerId +
                ", sessionId='" + sessionId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                ", lastActivityDate=" + lastActivityDate +
                '}';
    }
}
