package com.deploy.bemyplan.domain.plan;

public enum RcmndStatus {
    RECOMMENDED,
    NONE;

    public static RcmndStatus of(boolean recommend) {
        return recommend ? RECOMMENDED : NONE;
    }
}