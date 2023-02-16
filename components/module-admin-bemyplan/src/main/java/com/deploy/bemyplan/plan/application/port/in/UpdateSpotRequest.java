package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UpdateSpotRequest {
    private final long id;
    private final SpotCategoryType type;
    private final int date;
    private final String address;
    private final double latitude;
    private final double longitude;
    private final String name;
    private final String review;
    private final List<String> savedImages;
    private final String tip;
    private final TravelMobility vehicle;
    private final Integer spentTime;
}
