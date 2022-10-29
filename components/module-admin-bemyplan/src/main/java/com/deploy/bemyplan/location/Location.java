package com.deploy.bemyplan.location;

import lombok.Getter;

@Getter
public class Location {

    private static final Location EMPTY = new Location(0, 0);
    private double longitude;
    private double latitude;

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static Location empty() {
        return EMPTY;
    }

    public static Location of(double longitude, double latitude) {
        return new Location(longitude, latitude);
    }
}
