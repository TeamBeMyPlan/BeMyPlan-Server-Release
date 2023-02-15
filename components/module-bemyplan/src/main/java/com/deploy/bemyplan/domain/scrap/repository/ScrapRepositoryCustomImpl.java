package com.deploy.bemyplan.domain.scrap.repository;

import com.deploy.bemyplan.domain.scrap.Scrap;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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
    public Scrap findActiveByUserIdAndPlanId(Long planId, Long userId){
        if (userId == null) {
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