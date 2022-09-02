package com.deploy.bemyplan.domain.plan;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TravelTheme {
    HEALING("힐링"),
    EATING("맛집"),
    HOTPLACE("핫플"),
    LIFESHOT("인생샷"),
    LOCAL("로컬"),
    ACTIVITY("액티비티"),
    CAMPING("캠핑"),
    ;

    private final String value;
}
