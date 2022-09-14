package com.deploy.bemyplan.domain.order;

import com.deploy.bemyplan.domain.common.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders", uniqueConstraints = @UniqueConstraint(columnNames = {"planId", "userId"}))
public class Order extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long planId;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private int orderPrice;

    private Order(Long planId, Long userId, OrderStatus status, int price) {
        this.planId = planId;
        this.userId = userId;
        this.status = status;
        this.orderPrice = price;
    }

    public static Order of(Long planId, Long userId, OrderStatus status, int price) {
        return new Order(planId, userId, status, price);
    }

    public void orderComplete(){
        this.status = OrderStatus.ACTIVE;
    }
}