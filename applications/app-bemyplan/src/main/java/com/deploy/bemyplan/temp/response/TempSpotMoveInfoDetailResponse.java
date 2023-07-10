package com.deploy.bemyplan.temp.response;


import com.deploy.bemyplan.domain.plan.TravelMobility;

public class TempSpotMoveInfoDetailResponse {

    private Long fromSpotId;
    private Long toSpotId;
    private TravelMobility mobility;
    private int spentMinute;

    private TempSpotMoveInfoDetailResponse(Long fromSpotId, Long toSpotId, TravelMobility mobility, int spentMinute) {
        this.fromSpotId = fromSpotId;
        this.toSpotId = toSpotId;
        this.mobility = mobility;
        this.spentMinute = spentMinute;
    }

    private TempSpotMoveInfoDetailResponse() {
    }

    public static TempSpotMoveInfoDetailResponse of(Long fromSpotId, Long toSpotId, TravelMobility mobility, int spentMinute) {
        return new TempSpotMoveInfoDetailResponse(fromSpotId, toSpotId, mobility, spentMinute);
    }

    public Long getFromSpotId() {
        return this.fromSpotId;
    }

    public Long getToSpotId() {
        return this.toSpotId;
    }

    public TravelMobility getMobility() {
        return this.mobility;
    }

    public int getSpentMinute() {
        return this.spentMinute;
    }

    public String toString() {
        return "TempSpotMoveInfoDetailResponse(fromSpotId=" + this.getFromSpotId() + ", toSpotId=" + this.getToSpotId() + ", mobility=" + this.getMobility() + ", spentMinute=" + this.getSpentMinute() + ")";
    }
}
