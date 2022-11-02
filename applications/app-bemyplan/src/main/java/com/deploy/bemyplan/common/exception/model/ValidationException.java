package com.deploy.bemyplan.common.exception.model;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}