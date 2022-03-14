package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotContent;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotDetailResponse {

    private String name;
    private double latitude;
    private double longitude;

    private List<SpotDetailContentsResponse> contents = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private SpotDetailResponse(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static SpotDetailResponse of(@NotNull Spot spot, List<SpotContent> contents) {
        SpotDetailResponse response = new SpotDetailResponse(
                spot.getTitle(),
                spot.getLocation().getLatitude(),
                spot.getLocation().getLongitude()
        );
        response.contents.addAll(
                contents.stream()
                        .map(content -> SpotDetailContentsResponse.of(content))
                        .collect(Collectors.toList())
        );
        return response;
    }
}
