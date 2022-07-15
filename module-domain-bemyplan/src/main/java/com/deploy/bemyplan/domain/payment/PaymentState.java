package com.deploy.bemyplan.domain.payment;

import java.util.Arrays;

public enum PaymentState {
    FAIL(1),
    COMPLETE(0),
    UNDEFINED(-1);

    private final int status;

    PaymentState(final int status) {
        this.status = status;
    }

    public static PaymentState fromCode(int status) {
        return Arrays.stream(values())
                .filter(state -> state.status == status)
                .findAny()
                .orElse(UNDEFINED);
    }
}
