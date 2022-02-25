package com.deploy.bemyplan.domain.user.repository;

import com.deploy.bemyplan.domain.user.UserSocialType;

public interface UserRepositoryCustom {

    boolean existsByName(String name);
    boolean existsBySocialIdAndSocialType(String socialId, UserSocialType socialType);
}
