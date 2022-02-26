package com.deploy.bemyplan.domain.post;

import com.deploy.bemyplan.domain.post.repository.PostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
