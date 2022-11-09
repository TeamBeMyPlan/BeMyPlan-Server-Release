package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.SpotMoveInfo;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotMoveInfoDetailResponse {

    private Long fromSpotId;
    private Long toSpotId;
    private TravelMobility mobility;
    private int spentMinute;

    public static SpotMoveInfoDetailResponse of(@NotNull SpotMoveInfo moveInfo) {
        return new SpotMoveInfoDetailResponse(
                moveInfo.getFromSpotId(),
                moveInfo.getToSpotId(),
                moveInfo.getMobility(),
                moveInfo.getSpentMinute()
        );
    }
}