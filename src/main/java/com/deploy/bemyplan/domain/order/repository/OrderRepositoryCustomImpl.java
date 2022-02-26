package com.deploy.bemyplan.domain.order.repository;

import com.deploy.bemyplan.domain.order.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.deploy.bemyplan.domain.order.QOrder.order;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findAllByIds(List<Long> orderIds) {
        return queryFactory
                .selectFrom(order).distinct()
                .where(
                        order.id.in(orderIds)
                )
                .orderBy(order.id.desc())
                .fetch();
    }

    @Override
    public List<Order> findByUserIdAndPlanIds(List<Long> planIds, Long userId) {
        List<Long> orderIds = queryFactory
                .select(order.id).distinct()
                .from(order)
                .where(
                        order.userId.eq(userId),
                        order.planId.in(planIds)
                )
                .fetch();
        return findAllByIds(orderIds);
    }
}
