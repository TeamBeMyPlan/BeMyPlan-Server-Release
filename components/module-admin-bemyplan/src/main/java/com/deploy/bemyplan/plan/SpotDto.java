package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotDto {
    private int seq;
    private String address;
    private SpotCategoryType type;
    private int date;
    private double latitude;
    private double longitude;
    private String name;
    private String review;
    private List<String> savedImages;
    private String tip;
    private TravelMobility vehicle;
    private int spentTime;
}
