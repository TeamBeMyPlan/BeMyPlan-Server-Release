package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotDto {
    private int id;
    private String address;
    private SpotCategoryType type;
    private int date;
    private boolean hasNext;
    private double latitude;
    private double longitude;
    private String name;
    private NextSpot nextSpot;
    private String review;
    private List<String> savedImages;
    private String tip;

    @Builder(builderMethodName = "testBuilder")
    SpotDto(int id, String address,
            SpotCategoryType type,
            int date, boolean hasNext, double latitude, double longitude, String name, NextSpot nextSpot, String review, List<String> savedImages, String tip) {
        this.id = id;
        this.address = address;
        this.date = date;
        this.hasNext = hasNext;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.nextSpot = nextSpot;
        this.review = review;
        this.savedImages = savedImages;
        this.tip = tip;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class NextSpot {
        private int id;
        private TravelMobility vehicle;
        private int spentTime;

        @Builder(builderMethodName = "testBuilder")
        NextSpot(final int id, final TravelMobility vehicle, final int spentTime) {
            this.id = id;
            this.vehicle = vehicle;
            this.spentTime = spentTime;
        }
    }
}
