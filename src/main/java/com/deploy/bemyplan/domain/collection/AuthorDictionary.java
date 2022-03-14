package com.deploy.bemyplan.domain.collection;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.service.user.UserServiceUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorDictionary {

    private final Map<Long, User> dictionary;

    public static AuthorDictionary of(List<Plan> plans, UserRepository userRepository) {
        return new AuthorDictionary(plans.stream()
                                    .collect(
                                            Collectors.toMap(
                                                    Plan::getId,
                                                    plan -> UserServiceUtils.findUserById(userRepository, plan.getUserId())
                                            )
                                    )
        );
    }

    public User getAuthorByPlanId(Long planId) {
        return dictionary.get(planId);
    }
}
