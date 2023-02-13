package com.deploy.bemyplan.plan.service.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotMoveInfoResponse {

    private int day;

    private final List<SpotMoveInfoDetailResponse> infos = new ArrayList<>();

    public SpotMoveInfoResponse(final int day, final List<SpotMoveInfoDetailResponse> infos) {
        this.day = day;
        this.infos.addAll(infos);
    }
}