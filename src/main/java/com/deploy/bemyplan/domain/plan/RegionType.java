package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.common.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.deploy.bemyplan.domain.plan.RegionType.RegionTypeStatus.ACTIVE;
import static com.deploy.bemyplan.domain.plan.RegionType.RegionTypeStatus.LOCKED;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum RegionType implements EnumModel {

    JEJU("제주", "thumbnail", ACTIVE, 1),
    BUSAN("부산", "thumbnail", ACTIVE, 2),
    GANGNEUNG("강릉", "thumbnail", LOCKED, 3),
    YEOSU("여수", "thumbnail", LOCKED, 4),
    GYEONGJU("경주", "thumbnail", ACTIVE, 5),
    TONGYEONG_GEOJE("통영/거제", "thumbnail1", LOCKED, 6)
    ;

    private final String regionName;
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
        return regionName;
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public enum RegionTypeStatus {
        LOCKED(true),
        ACTIVE(false),
        ;

        private final boolean isLocked;
    }
}
