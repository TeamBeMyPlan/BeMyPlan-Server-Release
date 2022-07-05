package com.deploy.bemyplan.service.collection;

import com.deploy.bemyplan.domain.scrap.Scrap;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ScrapDictionary {

    /**
     * 특정 여행일정 게시글에 대한 스크랩 존재여부 판단
     */
    private final Map<Long, Scrap> dictionary;

    public static ScrapDictionary of(List<Scrap> scraps) {
        return new ScrapDictionary(scraps.stream()
                .collect(Collectors.toMap(Scrap::getPlanId, scrap -> scrap)));
    }

    public boolean existByPlanId(Long planId) {
        return dictionary.containsKey(planId);
    }
}