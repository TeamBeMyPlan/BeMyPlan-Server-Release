package com.deploy.bemyplan.location;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationService {
    private final KakaoClient kakaoClient;

    public Location getLocation(final String query) {
        final KakaoLocationModel locationModel = kakaoClient.getLocation(
                "KakaoAK bf5114bc0752d42656ad0d7c29762799",
                query);

        return locationModel.getLocation();
    }
}
