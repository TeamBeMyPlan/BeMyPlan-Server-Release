package com.deploy.bemyplan.domain.scrap.repository;

import com.deploy.bemyplan.domain.scrap.QScrap;
import com.deploy.bemyplan.domain.scrap.Scrap;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
                        scrap.userId.eq(userId),
                        scrap.planId.in(planIds)
                )
                .fetch();
        return findAllByIds(scrapIds);
    }

    @Override
    public Optional<Scrap> findByUserIdAndPlanId(Long planId, Long userId){
        QScrap qScrap = scrap;
        Scrap scrap = queryFactory
                .selectFrom(qScrap)
                .where(qScrap.userId.eq(userId),
                        qScrap.planId.eq(planId))
                .fetchOne();
        return Optional.ofNullable(scrap);
    }
}