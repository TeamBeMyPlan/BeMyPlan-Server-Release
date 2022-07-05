package com.deploy.bemyplan.common.exception.model;

import com.deploy.bemyplan.common.exception.ErrorCode;

public class BadGatewayException extends BeMyPlanException {

    public BadGatewayException(String message) {
        super(message, ErrorCode.BAD_GATEWAY_EXCEPTION);
    }
}