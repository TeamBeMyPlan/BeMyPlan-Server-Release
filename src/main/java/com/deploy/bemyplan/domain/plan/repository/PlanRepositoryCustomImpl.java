package com.deploy.bemyplan.domain.plan.repository;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.deploy.bemyplan.domain.plan.QPlan.plan;

@RequiredArgsConstructor
public class PlanRepositoryCustomImpl implements PlanRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Plan> findAllByIds(List<Long> planIds) {
        return queryFactory
                .selectFrom(plan).distinct()
                .where(
                        plan.id.in(planIds)
                )
                .orderBy(plan.id.desc())
                .fetch();
    }

    @Override
    public List<Plan> findPopularsUsingCursor(int size, Long lastPlanId, Pageable pageable, RcmndStatus rcmndStatus) {
        JPAQuery<Plan> query = queryFactory
                .select(plan).distinct()
                .from(plan)
                .where(
                        lessThanId(lastPlanId),
                        plan.status.eq(PlanStatus.ACTIVE),
                        plan.rcmndStatus.eq(rcmndStatus)
                )
                .orderBy(plan.id.desc())
                .limit(size);

        if (pageable != null) {
            for (Sort.Order o : pageable.getSort()) {
                System.out.println(o.getClass());
                System.out.println(o.getProperty());
                PathBuilder<Object> orderByExpression = new PathBuilder<Object>(Object.class, o.getProperty());
                query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, orderByExpression.get(o.getProperty())));
            }
        }
        return query.fetch();
    }

    private BooleanExpression lessThanId(Long lastPlanId) {
        if (lastPlanId == null) {
            return null;
        }
        return plan.id.lt(lastPlanId);
    }
}
