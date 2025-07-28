package com.elokator.model.session;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerSessionHistoryModel {
    private String sessionId;
    private Integer customerId;
    private LocalDateTime loginDate;
    private LocalDateTime logoutDate;

    public CustomerSessionHistoryModel() {
    }

    public CustomerSessionHistoryModel(final Builder builder) {
        this.sessionId = builder.sessionId;
        this.customerId = builder.customerId;
        this.loginDate = builder.loginDate;
        this.logoutDate = builder.logoutDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(final LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public LocalDateTime getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(final LocalDateTime logoutDate) {
        this.logoutDate = logoutDate;
    }

    public static class Builder {
        private String sessionId;
        private Integer customerId;
        private LocalDateTime loginDate;
        private LocalDateTime logoutDate;

        public Builder withSessionId(final String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder withCustomerId(final Integer customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withLoginDate(final LocalDateTime loginDate) {
            this.loginDate = loginDate;
            return this;
        }

        public Builder withLogoutDate(final LocalDateTime logoutDate) {
            this.logoutDate = logoutDate;
            return this;
        }

        public CustomerSessionHistoryModel build() {
            return new CustomerSessionHistoryModel(this);
        }
    }

    @Override
    public String toString() {
        return "CustomerSessionHistoryModel{" +
                "sessionId='" + sessionId + '\'' +
                ", customerId=" + customerId +
                ", loginDate=" + loginDate +
                ", logoutDate=" + logoutDate +
                '}';
    }
}
