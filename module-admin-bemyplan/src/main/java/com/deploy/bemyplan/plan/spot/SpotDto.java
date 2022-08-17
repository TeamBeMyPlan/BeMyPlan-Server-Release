package com.deploy.bemyplan.plan.spot;

import lombok.Getter;

import java.util.List;

@Getter
public class SpotDto {
    private int id;
    private String address;
    private int date;
    private boolean hasNext;
    private double latitude;
    private double longitude;
    private String name;
    private NextSpot nextSpot;
    private String review;
    private List<String> savedImages;
    private String tip;

    @Getter
    public static class NextSpot {
        private int id;
        private String vehicle;
        private int spentTime;
    }
}
