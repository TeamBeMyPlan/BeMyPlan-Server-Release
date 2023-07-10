package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.domain.scrap.Scrap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScrapDictionary {

    /**
     * 특정 여행일정 게시글에 대한 스크랩 존재여부 판단
     */
    private final Map<Long, Scrap> dictionary;

    private ScrapDictionary(Map<Long, Scrap> dictionary) {
        this.dictionary = dictionary;
    }

    public static ScrapDictionary of(List<Scrap> scraps) {
        return new ScrapDictionary(scraps.stream()
                .collect(Collectors.toMap(Scrap::getPlanId, scrap -> scrap)));
    }

    public boolean existByPlanId(Long planId) {
        return dictionary.containsKey(planId);
    }
}