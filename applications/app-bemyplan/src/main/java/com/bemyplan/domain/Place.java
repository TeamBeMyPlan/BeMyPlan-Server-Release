package com.bemyplan.domain;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

import static com.bemyplan.domain.Region.JEJUALL;
import static com.bemyplan.domain.Region.JEJUCITY;
import static com.bemyplan.domain.Region.JEJUEAST;
import static com.bemyplan.domain.Region.JEJUWEST;

@RequiredArgsConstructor
public enum Place {
    JEJU("제주", List.of(JEJUWEST, JEJUEAST, JEJUCITY, JEJUALL), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%EC%A0%9C%EC%A3%BC.png", false),
    JAPAN("일본", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%87%E1%85%A9%E1%86%AB.jpg", true),
    THAILAND("태국", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%E1%84%90%E1%85%A2%E1%84%80%E1%85%AE%E1%86%A8.jpg", true),
    VIETNAM("베트남", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%E1%84%87%E1%85%A6%E1%84%90%E1%85%B3%E1%84%82%E1%85%A1%E1%86%B7.jpg", true),
    TAIWAN("대만", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%E1%84%83%E1%85%A2%E1%84%86%E1%85%A1%E1%86%AB.jpg", true),
    SINGAPORE("싱가포르", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%E1%84%89%E1%85%B5%E1%86%BC%E1%84%80%E1%85%A1%E1%84%91%E1%85%A9%E1%84%85%E1%85%B3.jpg", true);
    ;

    private final String regionCategoryName;
    private final List<Region> regions;
    private final String thumbnailUrl;
    private final boolean isLocked;
}
