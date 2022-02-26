package com.deploy.bemyplan.domain.post.repository;

import com.deploy.bemyplan.domain.post.Post;
import com.deploy.bemyplan.domain.post.PostStatus;
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

import static com.deploy.bemyplan.domain.post.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> findAllByIds(List<Long> postIds) {
        return queryFactory
                .selectFrom(post).distinct()
                .where(
                        post.id.in(postIds)
                )
                .orderBy(post.id.desc())
                .fetch();
    }

    @Override
    public List<Post> findPopularsUsingCursor(int size, Long lastPlanId, Pageable pageable) {
        JPAQuery<Post> query = queryFactory
                .select(post).distinct()
                .from(post)
                .where(
                        lessThanId(lastPlanId),
                        post.status.eq(PostStatus.ACTIVE)
                )
                .orderBy(post.id.desc())
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
        return post.id.lt(lastPlanId);
    }
}
