package com.deploy.bemyplan.domain.payment;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import com.deploy.bemyplan.domain.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends AuditingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @NotNull
    private String transactionId;
    @NotNull
    @Enumerated(EnumType.STRING)
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
}
