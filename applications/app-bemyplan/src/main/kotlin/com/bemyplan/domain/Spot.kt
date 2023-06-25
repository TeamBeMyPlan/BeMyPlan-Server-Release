package com.bemyplan.domain;

import java.util.ArrayList;
import java.util.List;

public class Spot {
    private Long id;
    private String title;
    private SpotCategory category;
    private String address;
    private double latitude;
    private double longitude;
    private String tip;
    private String review;
    private List<SpotImage> images = new ArrayList<>();

    private int day;
    private TravelMobility vehicle;
    private Integer spentMinute;
}
