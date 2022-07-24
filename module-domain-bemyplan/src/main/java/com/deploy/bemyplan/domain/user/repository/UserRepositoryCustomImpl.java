package com.deploy.bemyplan.domain.user.repository;

import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserSocialType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.LockModeType;

import static com.deploy.bemyplan.domain.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existsByName(String name) {
        return queryFactory.selectOne()
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .setHint("javax.persistence.lock.timeout", 3000)
                .from(user)
                .where(user.nickname.eq(name),
                        user.status.isTrue()
                ).fetchFirst() != null;
    }

    @Override
    public boolean existsBySocialIdAndSocialType(String socialId, UserSocialType socialType) {
        return queryFactory.selectOne()
                .from(user)
                .where(
                        user.socialInfo.socialId.eq(socialId),
                        user.socialInfo.socialType.eq(socialType),
                        user.status.isTrue()
                ).fetchFirst() != null;
    }

    @Override
    public User findUserById(Long userId) {
        return queryFactory.selectFrom(user)
                .where(user.id.eq(userId),
                        user.status.isTrue()
                ).fetchOne();
    }

    @Override
    public User findUserBySocialIdAndSocialType(String socialId, UserSocialType socialType) {
        return queryFactory.selectFrom(user)
                .where(
                        user.socialInfo.socialId.eq(socialId),
                        user.socialInfo.socialType.eq(socialType),
                        user.status.isTrue()
                ).fetchOne();
    }
}