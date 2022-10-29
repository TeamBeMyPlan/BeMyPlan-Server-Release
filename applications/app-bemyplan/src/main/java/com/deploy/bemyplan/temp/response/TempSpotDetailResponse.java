package com.deploy.bemyplan.temp.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TempSpotDetailResponse {
    private String name;
    private double latitude;
    private double longitude;
    private String tip;
    private String review;
    private List<TempSpotImageResponse> images = new ArrayList<>();

    public static TempSpotDetailResponse of(String name, double latitude, double longitude, String tip, String review, List<TempSpotImageResponse> images){
        return new TempSpotDetailResponse(name, latitude, longitude, tip, review, images);

    }

}
