package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.plan.SpotMoveInfo;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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