package com.deploy.bemyplan.domain.order.repository;

import com.deploy.bemyplan.domain.order.Order;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.deploy.bemyplan.domain.order.QOrder.order;

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
        if (Objects.isNull(userId)) {
            return new ArrayList<>();
        }
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

    @Override
    public Order findByUserIdAndPlanId(Long planId, Long userId){
        if (Objects.isNull(userId)) {
            return null;
        }
        return queryFactory
                .selectFrom(order)
                .where(
                        eqUserId(userId),
                        eqPlanId(planId)
                )
                .fetchOne();
    }

    private BooleanExpression eqUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        return order.userId.eq(userId);
    }

    private BooleanExpression eqPlanId(Long planId) {
        if (Objects.isNull(planId)) {
            return null;
        }
        return order.planId.eq(planId);
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
