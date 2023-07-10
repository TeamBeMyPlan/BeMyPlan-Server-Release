package com.deploy.bemyplan.payment.service.utils.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class AppStoreResponse {
    private String environment;
    @JsonAlias("is-retryable")
    private boolean isRetryable;
    private Receipt receipt;
    private int status;

    public String getEnvironment() {
        return this.environment;
    }

    public boolean isRetryable() {
        return this.isRetryable;
    }

    public Receipt getReceipt() {
        return this.receipt;
    }

    public int getStatus() {
        return this.status;
    }

    public String toString() {
        return "AppStoreResponse(environment=" + this.getEnvironment() + ", isRetryable=" + this.isRetryable() + ", receipt=" + this.getReceipt() + ", status=" + this.getStatus() + ")";
    }
}
