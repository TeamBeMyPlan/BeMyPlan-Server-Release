package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.common.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Region implements EnumModel {
    JEJUWEST("제주서부", RegionCategory.JEJU),
    JEJUEAST("제주동부", RegionCategory.JEJU),
    JEJUCITY("제주시내", RegionCategory.JEJU),
    JEJUALL("제주전체", RegionCategory.JEJU),
    ;

    private final String regionName;
    private final RegionCategory regionCategory;

    @Override
    public String getKey() {
        return null;
    }

    @Override
    public String getValue() {
        return regionName;
    }
}
