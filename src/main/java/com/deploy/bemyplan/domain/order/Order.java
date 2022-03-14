package com.deploy.bemyplan.domain.order;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private Long userId;

    @Builder(access = AccessLevel.PRIVATE)
    private Order(Long planId, Long userId) {
        this.planId = planId;
        this.userId = userId;
    }

    public static Order of(Long planId, Long userId) {
        return Order.builder()
                .planId(planId)
                .userId(userId)
                .build();
    }
}