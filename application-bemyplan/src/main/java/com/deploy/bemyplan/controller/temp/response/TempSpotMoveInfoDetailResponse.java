package com.deploy.bemyplan.controller.temp.response;


import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TempSpotMoveInfoDetailResponse {

    private Long fromSpotId;
    private Long toSpotId;
    private TravelMobility mobility;
    private int spentMinute;

    public static TempSpotMoveInfoDetailResponse of(Long fromSpotId, Long toSpotId, TravelMobility mobility, int spentMinute){
        return new TempSpotMoveInfoDetailResponse(fromSpotId, toSpotId, mobility, spentMinute);
    }
}
