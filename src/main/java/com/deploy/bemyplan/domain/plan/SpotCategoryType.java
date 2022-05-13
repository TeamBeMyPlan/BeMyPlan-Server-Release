package com.deploy.bemyplan.domain.plan;

import com.deploy.bemyplan.common.model.EnumModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SpotCategoryType implements EnumModel {
    RESTAURANT("식당"),
    CAFE("카페"),
    MUSEUM("박물관"),
    TOURSPOT("관광지"),
    BEACH("바다"),
    BAR("술집"),
    STORE("잡화점"),
    ACCOMODATIONS("숙소"),
    BAKERY("베이커리"),
    LIQUORSTORE("주류점"),
    BOOKSTORE("서점"),
    MARKET("시장"),
    ARTMUSEUM("미술관"),
    ;

    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
