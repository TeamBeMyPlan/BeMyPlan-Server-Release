package com.deploy.bemyplan.domain.plan.repository;

import com.deploy.bemyplan.domain.order.OrderStatus;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;

import static com.deploy.bemyplan.domain.order.QOrder.order;
import static com.deploy.bemyplan.domain.plan.QPlan.plan;
import static com.deploy.bemyplan.domain.scrap.QScrap.scrap;

@RequiredArgsConstructor
public class PlanRepositoryCustomImpl implements PlanRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Plan> findMyBookmarkListUsingCursor(Long userId, @Nullable Pageable pageable, int size, @Nullable Long lastScrapId) {
        JPAQuery<Plan> query = queryFactory
                .selectFrom(plan).distinct()
                .where(
                        inPlanIdsWithScrap(userId, lastScrapId, size),
                        plan.status.eq(PlanStatus.ACTIVE)
                );
        /*
        스크랩 목록 조회할 때 sort=id,desc 과 같이 기준을 id로 넘긴다면 plan.id로 정렬이 된다. (scrap.id)
         */
        setDynamicSortCondition(pageable, query);
        return query.fetch();
    }

    @Override
    public List<Plan> findMyOrderListUsingCursor(Long userId, @Nullable Pageable pageable, int size, @Nullable Long lastOrderId) {
        JPAQuery<Plan> query = queryFactory
                .selectFrom(plan).distinct()
                .where(
                        inPlanIdsWithOrder(userId, lastOrderId, size),
                        plan.status.eq(PlanStatus.ACTIVE)
                );
        setDynamicSortCondition(pageable, query);
        return query.fetch();
    }

    private void setDynamicSortCondition(@Nullable Pageable pageable, JPAQuery<Plan> query) {
        if (!Objects.isNull(pageable)) {
            for (Sort.Order o : pageable.getSort()) {
                PathBuilder<Object> orderByExpression = new PathBuilder<Object>(Object.class, o.getProperty());
                query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, orderByExpression.get(o.getProperty())));
            }
        }
    }

    private BooleanExpression lessThanScrapId(Long lastScrapId) {
        if (Objects.isNull(lastScrapId)) {
            return null;
        }
        return scrap.id.lt(lastScrapId);
    }

    private BooleanExpression lessThanOrderId(Long lastOrderId) {
        if (Objects.isNull(lastOrderId)) {
            return null;
        }
        return order.id.lt(lastOrderId);
    }

    private BooleanExpression inPlanIdsWithScrap(Long userId, Long lastScrapId, int size) {
        List<Long> planIds = queryFactory.
                select(scrap.planId).distinct()
                .from(scrap)
                .where(
                        scrap.userId.eq(userId),
                        lessThanScrapId(lastScrapId)
                )
                .limit(size)
                .fetch();
        if (Objects.isNull(planIds)) {
            return null;
        }
        return plan.id.in(planIds);
    }

    private BooleanExpression inPlanIdsWithOrder(Long userId, Long lastOrderId, int size) {
        List<Long> planIds = queryFactory.
                select(order.planId)
                .from(order)
                .where(
                        order.userId.eq(userId),
                        order.status.eq(OrderStatus.COMPLETED),
                        lessThanOrderId(lastOrderId)
                )
                .orderBy(order.id.desc())
                .limit(size)
                .fetch();
        if (Objects.isNull(planIds)) {
            return null;
        }
        return plan.id.in(planIds);
    }
}
