package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.common.dto.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Spot;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotDetailResponse extends AuditingTimeResponse {

    private String name;
    private double latitude;
    private double longitude;
    private String tip;
    private String review;

    private final List<SpotImageResponse> images = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private SpotDetailResponse(String name, double latitude, double longitude, String tip, String review) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tip = tip;
        this.review = review;
    }

    public static SpotDetailResponse of(@NotNull Spot spot) {
        SpotDetailResponse response = new SpotDetailResponse(
                spot.getTitle(),
                spot.getLocation().getLatitude(),
                spot.getLocation().getLongitude(),
                spot.getTip(),
                spot.getReview()
        );
        response.images.addAll(
                spot.getImages().stream()
                        .map(SpotImageResponse::of)
                        .collect(Collectors.toList())
        );
        response.setBaseTime(spot);
        return response;
    }
}
