package com.deploy.bemyplan.domain.plan;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class Location {

    private static final double MIN_LATITUDE = -90.0;
    private static final double MAX_LATITUDE = 90.0;

    private static final double MIN_LONGITUDE = -180.0;
    private static final double MAX_LONGITUDE = 180.0;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    private Location(double latitude, double longitude) {
        validateScope(latitude, longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private void validateScope(double latitude, double longitude) {
//        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
//            throw new InvalidException(String.format("허용되지 않는 위도 (%s)가 입력되었습니다. (%s ~ %s) 사이의 위도만 허용됩니다)", latitude, MIN_LATITUDE, MAX_LATITUDE), INVALID_LATITUDE_RANGE);
//        }
//        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
//            throw new InvalidException(String.format("허용되지 않는 경도 (%s)가 입력되었습니다. (%s ~ %s) 사이의 경도만 허용됩니다)", longitude, MIN_LONGITUDE, MAX_LONGITUDE), INVALID_LONGITUDE_RANGE);
//        }
    }

    public static Location of(double latitude, double longitude) {
        return new Location(latitude, longitude);
    }
}
