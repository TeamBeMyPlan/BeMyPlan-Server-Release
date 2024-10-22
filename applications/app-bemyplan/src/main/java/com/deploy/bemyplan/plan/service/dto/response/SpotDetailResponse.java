package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Spot;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotDetailResponse extends AuditingTimeResponse {

    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private List<String> tip;
    private String review;

    private final List<SpotImageResponse> images = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private SpotDetailResponse(String name, String address, double latitude, double longitude, String tip, String review, List<SpotImageResponse> images) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tip = StringUtils.hasText(tip) ? Arrays.stream(tip.split("\\$")).collect(Collectors.toList()) : Collections.emptyList();
        this.review = review;
        this.images.addAll(images);
    }

    public static SpotDetailResponse of(@NotNull Spot spot) {
        SpotDetailResponse response = new SpotDetailResponse(
                spot.getTitle(),
                spot.getLocation().getAddress(),
                spot.getLocation().getLatitude(),
                spot.getLocation().getLongitude(),
                spot.getTip(),
                spot.getReview(),
                spot.getImages().stream()
                        .map(SpotImageResponse::of)
                        .collect(Collectors.toList())
        );

        response.setBaseTime(spot.getCreatedAt(), spot.getUpdatedAt());
        return response;
    }
}
