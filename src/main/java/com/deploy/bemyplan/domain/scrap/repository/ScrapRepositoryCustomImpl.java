package com.deploy.bemyplan.domain.scrap.repository;

import com.deploy.bemyplan.domain.scrap.Scrap;
import com.deploy.bemyplan.domain.scrap.ScrapStatus;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.deploy.bemyplan.domain.scrap.QScrap.scrap;

@RequiredArgsConstructor
public class ScrapRepositoryCustomImpl implements ScrapRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Scrap> findAllByIds(List<Long> scrapIds) {
        return queryFactory
                .selectFrom(scrap).distinct()
                .where(
                        inScrapIds(scrapIds)
                )
                .orderBy(scrap.id.desc())
                .fetch();
    }

    @Override
    public List<Scrap> findByUserIdAndPlanIds(List<Long> planIds, @Nullable Long userId) {
        if (Objects.isNull(userId)) {
            return new ArrayList<>();
        }
        List<Long> scrapIds = queryFactory
                .select(scrap.id).distinct()
                .from(scrap)
                .where(
                        scrap.status.eq(ScrapStatus.ACTIVE),
                        eqUserId(userId),
                        inPlanIds(planIds)
                )
                .fetch();
        return findAllByIds(scrapIds);
    }

    @Override
    public Scrap findByUserIdAndPlanId(Long planId, Long userId){
        if (Objects.isNull(userId)) {
            return null;
        }
        return queryFactory
                .selectFrom(scrap)
                .where(
                        eqUserId(userId),
                        eqPlanId(planId)
                )
                .fetchOne();
    }

    @Override
    public Scrap findActiveByUserIdAndPlanId(Long planId, Long userId){
        if (userId == null) {
            return null;
        }
        return queryFactory
                .selectFrom(scrap)
                .where(
                        scrap.status.eq(ScrapStatus.ACTIVE),
                        eqUserId(userId),
                        eqPlanId(planId)
                )
                .fetchOne();
    }

    private BooleanExpression lessThanId(Long lastScrapId) {
        if (Objects.isNull(lastScrapId)) {
            return null;
        }
        return scrap.id.lt(lastScrapId);
    }

    private BooleanExpression eqUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        return scrap.userId.eq(userId);
    }

    private BooleanExpression eqPlanId(Long planId) {
        if (Objects.isNull(planId)) {
            return null;
        }
        return scrap.planId.eq(planId);
    }

    private BooleanExpression inScrapIds(List<Long> scrapIds) {
        if (Objects.isNull(scrapIds)) {
            return null;
        }
        return scrap.id.in(scrapIds);
    }

    private BooleanExpression inPlanIds(List<Long> planIds) {
        if (Objects.isNull(planIds)) {
            return null;
        }
        return scrap.planId.in(planIds);
    }
}