package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateSpotRequest {
    private long id;
    private SpotCategoryType type;
    private int date;
    private String address;
    private double latitude;
    private double longitude;
    private String name;
    private String review;
    private List<String> savedImages;
    private String tip;
    private TravelMobility vehicle;
    private Integer spentTime;
}
