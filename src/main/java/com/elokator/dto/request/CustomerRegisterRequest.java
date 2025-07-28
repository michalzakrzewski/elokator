package com.elokator.dto.request;

import com.elokator.dto.deserializer.CountryDeserializer;
import com.elokator.enums.CountryEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDate;

public class CustomerRegisterRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String loginName;
    private String emailAddress;
    private String mobileNumber;
    private String address;
    private String city;
    @JsonDeserialize(using = CountryDeserializer.class)
    private CountryEnum country;
    @JsonDeserialize(using = CountryDeserializer.class)
    private CountryEnum countryCode;
    private String peselNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

    public CustomerRegisterRequest() {
    }

    public CustomerRegisterRequest(final String firstName,
                                   final String lastName,
                                   final String password,
                                   final String loginName,
                                   final String emailAddress,
                                   final String mobileNumber,
                                   final String address,
                                   final String city,
                                   final CountryEnum country,
                                   final CountryEnum countryCode,
                                   final String peselNumber,
                                   final LocalDate dateOfBirth) {
        this.loginName = loginName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.countryCode = countryCode;
        this.emailAddress = emailAddress;
        this.mobileNumber = mobileNumber;
        this.peselNumber = peselNumber;
        this.dateOfBirth = dateOfBirth;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
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

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(final String peselNumber) {
        this.peselNumber = peselNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(final String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "CustomerRegisterRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", countryCode=" + countryCode +
                ", peselNumber=" + peselNumber +
                ", dateOfBirth='" + dateOfBirth +
                ", loginName='" + loginName +
                '}';
    }
}
