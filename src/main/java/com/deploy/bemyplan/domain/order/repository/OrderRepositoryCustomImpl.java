package com.deploy.bemyplan.domain.order.repository;

import com.deploy.bemyplan.domain.order.Order;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static com.deploy.bemyplan.domain.order.QOrder.order;
import static com.deploy.bemyplan.domain.scrap.QScrap.scrap;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findAllByIds(List<Long> orderIds) {
        return queryFactory
                .selectFrom(order).distinct()
                .where(
                        inOrderIds(orderIds)
                )
                .orderBy(order.id.desc())
                .fetch();
    }

    @Override
    public List<Order> findByUserIdAndPlanIds(List<Long> planIds, @Nullable Long userId) {
        List<Long> orderIds = queryFactory
                .select(order.id).distinct()
                .from(order)
                .where(
                        eqUserId(userId),
                        inPlanIds(planIds)
                )
                .fetch();
        return findAllByIds(orderIds);
    }

    private BooleanExpression eqUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        return order.userId.eq(userId);
    }

    private BooleanExpression inOrderIds(List<Long> orderIds) {
        if (Objects.isNull(orderIds)) {
            return null;
        }
        return order.id.in(orderIds);
    }

    private BooleanExpression inPlanIds(List<Long> planIds) {
        if (Objects.isNull(planIds)) {
            return null;
        }
        return order.planId.in(planIds);
    }
}
