package com.deploy.bemyplan.temp.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TempSpotDetailResponse extends AuditingTimeResponse {
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String tip;
    private String review;

    private List<TempSpotImageResponse> images = new ArrayList<>();

    private TempSpotDetailResponse(final String name, final String address, final double latitude, final double longitude, final String tip, final String review, final List<TempSpotImageResponse> images) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tip = tip;
        this.review = review;
        this.images = images;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static TempSpotDetailResponse of(String name, String address, double latitude, double longitude, String tip, String review, List<TempSpotImageResponse> images) {
        return new TempSpotDetailResponse(name, address, latitude, longitude, tip, review, images);
    }
}
