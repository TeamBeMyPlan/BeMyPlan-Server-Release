package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.common.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

import static com.deploy.bemyplan.domain.plan.Region.JEJUALL;
import static com.deploy.bemyplan.domain.plan.Region.JEJUCITY;
import static com.deploy.bemyplan.domain.plan.Region.JEJUEAST;
import static com.deploy.bemyplan.domain.plan.Region.JEJUWEST;
import static com.deploy.bemyplan.domain.plan.RegionCategory.RegionTypeStatus.ACTIVE;
import static com.deploy.bemyplan.domain.plan.RegionCategory.RegionTypeStatus.LOCKED;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum RegionCategory implements EnumModel {

    JEJU("제주", List.of(JEJUWEST, JEJUEAST, JEJUCITY, JEJUALL), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%EC%A0%9C%EC%A3%BC.png", ACTIVE, 1),
    BUSAN("부산", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%EB%B6%80%EC%82%B0.png", LOCKED, 2),
    GANGNEUNG("강릉", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%EA%B0%95%EB%A6%89.png", LOCKED, 3),
    YEOSU("여수", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%EC%97%AC%EC%88%98.png", LOCKED, 4),
    GYEONGJU("경주", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%EA%B2%BD%EC%A3%BC.png", LOCKED, 5),
    TONGYEONG_GEOJE("통영/거제", Collections.emptyList(), "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EC%97%AC%ED%96%89%EC%A7%80%EC%82%AC%EC%A7%84/%ED%86%B5%EC%98%81_%EA%B1%B0%EC%A0%9C.png", LOCKED, 6);

    private final String regionCategoryName;
    private final List<Region> regions;
    private final String thumbnailUrl;
    private final RegionTypeStatus status;
    private final int displayOrder;

    public boolean isLocked() {
        return this.status.isLocked;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return regionCategoryName;
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public enum RegionTypeStatus {
        LOCKED(true),
        ACTIVE(false),
        ;
        private final boolean isLocked;
    }
}
