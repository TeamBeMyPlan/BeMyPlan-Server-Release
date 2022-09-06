package com.deploy.bemyplan.domain.plan;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Region {

    JEJUWEST("제주서부"),
    JEJUEAST("제주동부"),
    JEJUCITY("제주시내"),
    JEJUALL("제주전체"),
    ;

    private final String regionName;
}
