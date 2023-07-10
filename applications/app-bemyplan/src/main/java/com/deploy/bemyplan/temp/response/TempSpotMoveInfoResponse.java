package com.deploy.bemyplan.temp.response;


import java.util.ArrayList;
import java.util.List;

public class TempSpotMoveInfoResponse {

    private int day;
    private List<TempSpotMoveInfoDetailResponse> infos = new ArrayList<>();

    private TempSpotMoveInfoResponse(int day, List<TempSpotMoveInfoDetailResponse> infos) {
        this.day = day;
        this.infos = infos;
    }

    private TempSpotMoveInfoResponse() {
    }

    public static TempSpotMoveInfoResponse of(int day, List<TempSpotMoveInfoDetailResponse> infos) {
        return new TempSpotMoveInfoResponse(day, infos);
    }

    public int getDay() {
        return this.day;
    }

    public List<TempSpotMoveInfoDetailResponse> getInfos() {
        return this.infos;
    }

    public String toString() {
        return "TempSpotMoveInfoResponse(day=" + this.getDay() + ", infos=" + this.getInfos() + ")";
    }
}
