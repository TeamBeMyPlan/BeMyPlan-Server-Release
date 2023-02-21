package com.deploy.bemyplan.domain.plan;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TravelMobility {
    CAR("승용차"),
    PUBLIC("대중교통"),
    WALK("도보"),
    BICYCLE("자전거"),
    NONE("");

    private final String value;
}