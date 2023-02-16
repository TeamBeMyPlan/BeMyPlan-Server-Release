package com.deploy.bemyplan.plan.adapter.in;

import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.plan.application.port.in.UpdateSpotRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateSpotParam {
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

    public UpdateSpotRequest toRequest() {
        return new UpdateSpotRequest(
                id,
                type,
                date,
                address,
                latitude,
                longitude,
                name,
                review,
                savedImages,
                tip,
                vehicle,
                spentTime
        );
    }
}
