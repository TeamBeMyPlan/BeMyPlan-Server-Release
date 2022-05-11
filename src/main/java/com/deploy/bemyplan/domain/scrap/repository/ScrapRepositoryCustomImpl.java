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
                        scrap.id.in(scrapIds)
                )
                .orderBy(scrap.id.desc())
                .fetch();
    }

    @Override
    public List<Scrap> findByUserIdAndPlanIds(List<Long> planIds, Long userId) {
        List<Long> scrapIds = queryFactory
                .select(scrap.id).distinct()
                .from(scrap)
                .where(
                        eqUserId(userId),
                        scrap.planId.in(planIds)
                )
                .fetch();
        return findAllByIds(scrapIds);
    }

    @Override
    public Scrap findByUserIdAndPlanId(Long planId, Long userId){
        return queryFactory
                .selectFrom(scrap)
                .where(
                        eqUserId(userId),
                        scrap.planId.eq(planId)
                )
                .fetchOne();
    }

    private BooleanExpression lessThanId(Long lastScrapId) {
        if (lastScrapId == null) {
            return null;
        }
        return scrap.id.lt(lastScrapId);
    }

    private BooleanExpression eqUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        return scrap.userId.eq(userId);
    }
}