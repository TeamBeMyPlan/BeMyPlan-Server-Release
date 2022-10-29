package com.deploy.bemyplan.common.exception.model;

import com.deploy.bemyplan.common.exception.ErrorCode;

public class ConflictException extends BeMyPlanException {

    public ConflictException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ConflictException(String message) {
        super(message, ErrorCode.CONFLICT_EXCEPTION);
    }
}