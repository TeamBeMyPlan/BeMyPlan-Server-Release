package com.deploy.bemyplan.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationService {
    private final KakaoClient kakaoClient;

    public Location getLocation(final String query) {
        final KakaoLocationModel locationModel = kakaoClient.getLocation(
                "KakaoAK 709f94b5966f382d201a09c119ccc325",
                query);

        return locationModel.getLocation();
    }
}
