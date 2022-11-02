package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Spot;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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