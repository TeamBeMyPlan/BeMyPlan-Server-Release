package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthorDictionary {

    private final Map<Long, Creator> dictionary;

    private AuthorDictionary(Map<Long, Creator> dictionary) {
        this.dictionary = dictionary;
    }

    public static AuthorDictionary of(List<Plan> plans, CreatorRepository creatorRepository) {
        return new AuthorDictionary(plans.stream()
                .collect(
                        Collectors.toMap(
                                Plan::getId,
                                plan -> creatorRepository.findById(plan.getCreatorId())
                                        .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다", plan.getCreatorId())))
                        )
                )
        );
    }

    public Creator getAuthorByPlanId(Long planId) {
        return dictionary.get(planId);
    }
}
