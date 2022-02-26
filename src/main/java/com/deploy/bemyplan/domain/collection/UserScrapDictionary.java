package com.deploy.bemyplan.domain.collection;

import com.deploy.bemyplan.domain.scrap.Scrap;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserScrapDictionary {

    /**
     * 특정 여행일정 게시글에 대한 스크랩 존재여부 판단
     */
    private final Map<Long, Scrap> dictionary;

    public static UserScrapDictionary of(List<Scrap> scraps) {
        return new UserScrapDictionary(scraps.stream()
                .collect(Collectors.toMap(Scrap::getPostId, scrap -> scrap)));
    }

    public boolean existByPlanId(Long planId) {
        return dictionary.containsKey(planId);
    }
}