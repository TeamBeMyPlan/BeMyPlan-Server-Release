package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.domain.plan.SpotImage;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    private SpotDto(final SpotCategoryType type, final int date, final String address, final double latitude, final double longitude, final String name, final String review, final List<String> savedImages, final String tip, final TravelMobility vehicle, final int spentTime) {
        this.type = type;
        this.date = date;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.review = review;
        this.savedImages = savedImages;
        this.tip = tip;
        this.vehicle = vehicle;
        this.spentTime = spentTime;
    }
    public static SpotDto from(Spot spot) {
        return new SpotDto(spot.getCategory(),
                1,
                spot.getLocation().getAddress(),
                spot.getLocation().getLatitude(),
                spot.getLocation().getLongitude(),
                spot.getTitle(),
                spot.getReview(),
                spot.getImages().stream().map(SpotImage::getUrl).collect(Collectors.toList()),
                spot.getTip(),
                spot.getVehicle(),
                spot.getSpentMinute());
    }
}
