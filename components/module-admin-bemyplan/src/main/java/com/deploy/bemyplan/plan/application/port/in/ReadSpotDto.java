package com.deploy.bemyplan.plan.application.port.in;

import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.domain.plan.SpotImage;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ReadSpotDto {
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

    public static ReadSpotDto from(Spot spot) {
        return new ReadSpotDto(
                spot.getId(),
                spot.getCategory(),
                spot.getDay(),
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
