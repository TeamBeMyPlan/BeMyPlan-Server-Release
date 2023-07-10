package com.deploy.bemyplan.temp.response;


import java.util.ArrayList;
import java.util.List;

public class TempScheduleDetailResponse {
    private List<TempSpotDetailResponse> spots = new ArrayList<>();

    private TempScheduleDetailResponse(List<TempSpotDetailResponse> spots) {
        this.spots = spots;
    }

    private TempScheduleDetailResponse() {
    }

    public static TempScheduleDetailResponse of(List<TempSpotDetailResponse> spots) {
        return new TempScheduleDetailResponse(spots);
    }

    public List<TempSpotDetailResponse> getSpots() {
        return this.spots;
    }

    public String toString() {
        return "TempScheduleDetailResponse(spots=" + this.getSpots() + ")";
    }
}
