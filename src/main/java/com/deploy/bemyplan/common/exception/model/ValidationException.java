package com.deploy.bemyplan.common.exception.model;

import com.deploy.bemyplan.common.exception.ErrorCode;

public class ValidationException extends BeMyPlanException {

    public ValidationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ValidationException(String message) {
        super(message, ErrorCode.VALIDATION_EXCEPTION);
    }
}