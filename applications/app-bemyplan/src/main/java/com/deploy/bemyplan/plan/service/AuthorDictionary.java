package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
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
                                                    plan -> userRepository.findById(plan.getUserId())
                                                            .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다", plan.getUserId())))
                                            )
                                    )
        );
    }

    public User getAuthorByPlanId(Long planId) {
        return dictionary.get(planId);
    }
}
