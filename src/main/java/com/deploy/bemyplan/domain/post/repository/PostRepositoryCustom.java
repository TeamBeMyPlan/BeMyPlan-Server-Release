package com.deploy.bemyplan.domain.post.repository;

import com.deploy.bemyplan.domain.post.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> findAllByIds(List<Long> postIds);
    List<Post> findPopularsUsingCursor(int size, Long lastPlanId, Pageable pageable);
}