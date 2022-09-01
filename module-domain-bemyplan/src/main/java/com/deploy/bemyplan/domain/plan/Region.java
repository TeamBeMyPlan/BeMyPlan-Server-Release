package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.common.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Region implements EnumModel {

    JEJUWEST("제주서부"),
    JEJUEAST("제주동부"),
    JEJUCITY("제주시내"),
    JEJUALL("제주전체"),
    ;

    private final String regionName;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return regionName;
    }
}
