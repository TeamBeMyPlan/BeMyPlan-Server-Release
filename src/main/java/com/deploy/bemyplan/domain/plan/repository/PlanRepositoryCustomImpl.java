package com.deploy.bemyplan.domain.plan.repository;

import com.deploy.bemyplan.domain.order.QOrder;
import com.deploy.bemyplan.domain.plan.*;
import com.deploy.bemyplan.domain.scrap.QScrap;
import com.deploy.bemyplan.domain.scrap.ScrapStatus;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;

import static com.deploy.bemyplan.domain.order.QOrder.order;
import static com.deploy.bemyplan.domain.plan.QDailySchedule.dailySchedule;
import static com.deploy.bemyplan.domain.plan.QPlan.plan;
import static com.deploy.bemyplan.domain.plan.QPreviewContent.previewContent;
import static com.deploy.bemyplan.domain.plan.QSpot.spot;
import static com.deploy.bemyplan.domain.plan.QSpotContent.spotContent;
import static com.deploy.bemyplan.domain.scrap.QScrap.scrap;
import static com.deploy.bemyplan.domain.user.QUser.user;

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
    public Plan findPlanById(Long planId) {
        return queryFactory.selectFrom(plan)
                .where(plan.id.eq(planId))
                .fetchOne();
    }

    @Override
    public Plan findPlanByIdFetchJoinSchedule(Long planId) {
        return queryFactory.selectFrom(plan).distinct()
                .innerJoin(plan.schedules, dailySchedule).fetchJoin()
//                .innerJoin(dailySchedule.spots, spot).fetchJoin()
//                .innerJoin(spot.contents, spotContent).fetchJoin()
                .where(
                        plan.id.eq(planId),
                        plan.status.eq(PlanStatus.ACTIVE)
                ).fetchOne();
    }

    @Override
    public List<Plan> findMyPlanBookmarksUsingCursor(Long userId, @Nullable Pageable pageable, int size, @Nullable Long lastScrapId) {
        JPAQuery<Plan> query = queryFactory
                .select(plan).distinct()
                .where(
                        plan.id.in(JPAExpressions
                                .select(scrap.planId).distinct()
                                .from(scrap)
                                .where(
                                        scrap.userId.eq(userId),
                                        scrap.status.eq(ScrapStatus.ACTIVE),
                                        lessThanScrapId(lastScrapId)
                                )
                                .orderBy(scrap.id.desc())
                                .limit(size)
                                .fetch()
                        )
                );
        setDynamicSortCondition(pageable, query);
        return query.fetch();
    }

    @Override
    public List<Plan> findMyPlansOrderedUsingCursor(Long userId, @Nullable Pageable pageable, int size, @Nullable Long lastOrderId) {
        JPAQuery<Plan> query = queryFactory
                .select(plan).distinct()
                .where(
                        plan.id.in(JPAExpressions
                                .select(order.planId).distinct()
                                .from(order)
                                .where(
                                        order.userId.eq(userId),
                                        lessThanOrderId(lastOrderId)
                                )
                                .orderBy(order.id.desc())
                                .limit(size)
                                .fetch()
                        )
                );
        setDynamicSortCondition(pageable, query);
        return query.fetch();
    }

    private void setDynamicSortCondition(@Nullable Pageable pageable, JPAQuery<Plan> query) {
        if (Objects.isNull(pageable)) {
            for (Sort.Order o : pageable.getSort()) {
                System.out.println(o.getClass());
                System.out.println(o.getProperty());
                PathBuilder<Object> orderByExpression = new PathBuilder<Object>(Object.class, o.getProperty());
                query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, orderByExpression.get(o.getProperty())));
            }
        }
    }

    @Override
    public List<Plan> findPlansUsingCursor(int size, Long lastPlanId, Pageable pageable, RegionType region, RcmndStatus rcmndStatus) {
        JPAQuery<Plan> query = queryFactory
                .select(plan).distinct()
                .from(plan)
                .where(
                        lessThanId(lastPlanId),
                        eqRegion(region),
                        plan.status.eq(PlanStatus.ACTIVE),
                        plan.rcmndStatus.eq(rcmndStatus)
                )
                .orderBy(plan.id.desc())
                .limit(size);

        setDynamicSortCondition(pageable, query);
        return query.fetch();
    }

    @Override
    public List<PreviewContent> findPreviewContentsByPlanId(Long planId) {
        return queryFactory
                .selectFrom(previewContent)
                .where(
                        previewContent.plan.id.eq(planId),
                        previewContent.status.eq(PreviewContentStatus.ACTIVE)
                )
                .orderBy(previewContent.id.asc())
                .fetch();
    }

    @Override
    public List<DailySchedule> findSchedulesByPlanId(Long planId) {
        return queryFactory
                .selectFrom(dailySchedule)
                .where(
                        dailySchedule.plan.id.eq(planId)
                )
                .orderBy(dailySchedule.day.asc())
                .fetch();
    }

    private BooleanExpression lessThanId(Long lastPlanId) {
        if (lastPlanId == null) {
            return null;
        }
        return plan.id.lt(lastPlanId);
    }

    private BooleanExpression lessThanScrapId(Long lastScrapId) {
        if (lastScrapId == null) {
            return null;
        }
        return scrap.id.lt(lastScrapId);
    }

    private BooleanExpression lessThanOrderId(Long lastOrderId) {
        if (lastOrderId == null) {
            return null;
        }
        return order.id.lt(lastOrderId);
    }

    private BooleanExpression eqRegion(@Nullable RegionType regionType) {
        if (regionType == null) {
            return null;
        }
        return plan.region.eq(regionType);
    }
}
