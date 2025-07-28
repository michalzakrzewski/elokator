package com.elokator.model.customer;

import com.elokator.enums.AccountStatusEnum;
import com.elokator.enums.CountryEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerAccountModel {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
    private CountryEnum country;
    private CountryEnum countryCode;
    private String password;
    private AccountStatusEnum accountStatusEnum;
    private LocalDate dateOfBirth;
    private LocalDateTime registrationDate;

    public CustomerAccountModel() {
    }

    public CustomerAccountModel(final Builder builder) {
        this.customerId = builder.customerId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.emailAddress = builder.emailAddress;
        this.mobileNumber = builder.mobileNumber;
        this.country = builder.country;
        this.countryCode = builder.countryCode;
        this.password = builder.password;
        this.accountStatusEnum = builder.accountStatusEnum;
        this.dateOfBirth = builder.dateOfBirth;
        this.registrationDate = builder.registrationDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public CountryEnum getCountry() {
        return country;
    }

    public void setCountry(final CountryEnum country) {
        this.country = country;
    }

    public CountryEnum getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(final CountryEnum countryCode) {
        this.countryCode = countryCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public AccountStatusEnum getAccountStatusEnum() {
        return accountStatusEnum;
    }

    public void setAccountStatusEnum(final AccountStatusEnum accountStatusEnum) {
        this.accountStatusEnum = accountStatusEnum;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(final LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public static class Builder {
        private Integer customerId;
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String mobileNumber;
        private CountryEnum country;
        private CountryEnum countryCode;
        private String password;
        private AccountStatusEnum accountStatusEnum;
        private LocalDate dateOfBirth;
        private LocalDateTime registrationDate;

        public Builder withCustomerId(final Integer customerId) {
            this.customerId = customerId;
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

        public Builder withCountry(final CountryEnum country) {
            this.country = country;
            return this;
        }

        public Builder withCountryCode(final CountryEnum countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder withPassword(final String password) {
            this.password = password;
            return this;
        }

        public Builder withAccountStatusEnum(final AccountStatusEnum accountStatusEnum) {
            this.accountStatusEnum = accountStatusEnum;
            return this;
        }

        public Builder withDateOfBirth(final LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder withRegistrationDate(final LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public CustomerAccountModel build() {
            return new CustomerAccountModel(this);
        }
    }

    @Override
    public String toString() {
        return "CustomerAccountModel{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", country=" + country +
                ", countryCode=" + countryCode +
                ", password='" + password + '\'' +
                ", accountStatusEnum=" + accountStatusEnum +
                ", dateOfBirth=" + dateOfBirth +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
