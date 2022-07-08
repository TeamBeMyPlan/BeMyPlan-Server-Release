package com.deploy.bemyplan.domain.payment;

import com.deploy.bemyplan.domain.order.Order;

public class Payment {
    private Long id;
    private Order order;
    private String transactionId;
    private PaymentState paymentState;

    private Payment(final Long id, final Order order, final String transactionId, final PaymentState state) {
        this.id = id;
        this.order = order;
        this.transactionId = transactionId;
        this.paymentState = state;
    }

    public static Payment of(final Order order, final String transactionId, final PaymentState state) {
        return new Payment(null, order, transactionId, state);
    }

    public void validate() {
        order.validate();
    }
}
