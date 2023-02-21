package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotMoveInfoDetailResponse {

    private Long fromSpotId;
    private Long toSpotId;
    private TravelMobility mobility;
    private int spentMinute;

    public SpotMoveInfoDetailResponse(final Long fromSpotId, final Long toSpotId, final TravelMobility mobility, final int spentMinute) {
        this.fromSpotId = fromSpotId;
        this.toSpotId = toSpotId;
        this.mobility = mobility;
        this.spentMinute = spentMinute;
    }
}