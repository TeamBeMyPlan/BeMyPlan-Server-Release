package com.deploy.bemyplan.common.exception.model;

import com.deploy.bemyplan.common.exception.ErrorCode;

public class UnAuthorizedException extends BeMyPlanException {

    public UnAuthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_EXCEPTION);
    }
}