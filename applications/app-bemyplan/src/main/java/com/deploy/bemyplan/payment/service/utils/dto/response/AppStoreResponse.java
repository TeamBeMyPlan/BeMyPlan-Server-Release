package com.deploy.bemyplan.payment.service.utils.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AppStoreResponse {
    private String environment;
    @JsonAlias("is-retryable")
    private boolean isRetryable;
    private Receipt receipt;
    private int status;
}
