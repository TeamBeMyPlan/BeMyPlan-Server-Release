package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Spot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleDetailResponse {

    private List<SpotDetailResponse> spots = new ArrayList<>();

    private ScheduleDetailResponse(List<SpotDetailResponse> spots) {
        this.spots = spots;
    }

    private ScheduleDetailResponse() {
    }

    public static ScheduleDetailResponse of(List<Spot> spots) {
        List<SpotDetailResponse> spotDetails = spots.stream()
                .map(SpotDetailResponse::of)
                .collect(Collectors.toList());

        return new ScheduleDetailResponse(spotDetails);
    }

    public List<SpotDetailResponse> getSpots() {
        return this.spots;
    }

    public String toString() {
        return "ScheduleDetailResponse(spots=" + this.getSpots() + ")";
    }
}