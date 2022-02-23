package com.deploy.bemyplan.domain.post;

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
