package com.deploy.bemyplan.domain.plan;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum TravelPartner{
    FAMILY("가족"),
    FRIEND("친구"),
    COUPLE("연인"),
    SOLO("혼자"),
    DOG("반려견")
    ;

    private final String value;
}

