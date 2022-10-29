package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.DailySchedule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpotMoveInfoResponse {

    private int day;

    private final List<SpotMoveInfoDetailResponse> infos = new ArrayList<>();

    private SpotMoveInfoResponse(int day) {
        this.day = day;
    }

    public static SpotMoveInfoResponse of(@NotNull DailySchedule schedule) {
        SpotMoveInfoResponse response = new SpotMoveInfoResponse(schedule.getDay());

        response.infos.addAll(
                schedule.getMoveInfos().stream()
                        .map(SpotMoveInfoDetailResponse::of)
                        .collect(Collectors.toList())
        );
        return response;
    }
}