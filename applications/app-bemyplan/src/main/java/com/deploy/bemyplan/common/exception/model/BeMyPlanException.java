package com.deploy.bemyplan.common.exception.model;

import com.deploy.bemyplan.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public abstract class BeMyPlanException extends RuntimeException{

    private final ErrorCode errorCode;

    public BeMyPlanException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }
}
