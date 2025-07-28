package com.elokator.dto.request;

public class CustomerLoginRequest {
    private String emailAddress;
    private String password;

    public CustomerLoginRequest() {
    }

    public CustomerLoginRequest(final String emailAddress, final String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CustomerLoginRequest{" +
                "emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
