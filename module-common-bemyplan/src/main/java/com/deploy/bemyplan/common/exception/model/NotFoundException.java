package com.deploy.bemyplan.common.exception.model;

import com.deploy.bemyplan.common.exception.ErrorCode;

public class NotFoundException extends BeMyPlanException {

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotFoundException(String message) {
        super(message, ErrorCode.NOT_FOUND_EXCEPTION);
    }
}