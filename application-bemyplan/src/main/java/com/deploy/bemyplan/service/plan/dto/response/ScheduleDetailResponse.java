package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.plan.Spot;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ScheduleDetailResponse {

    private List<SpotDetailResponse> spots = new ArrayList<>();

    public static ScheduleDetailResponse of(List<Spot> spots) {
        ScheduleDetailResponse response = new ScheduleDetailResponse();
        response.spots.addAll(
                spots.stream()
                        .map(spot -> SpotDetailResponse.of(spot))
                        .collect(Collectors.toList())
        );
        return response;
    }
}