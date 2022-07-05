package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.plan.DailySchedule;
import lombok.*;
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

    @Builder(access = AccessLevel.PRIVATE)
    private SpotMoveInfoResponse(int day) {
        this.day = day;
    }

    public static SpotMoveInfoResponse of(@NotNull DailySchedule schedule) {
        SpotMoveInfoResponse response = SpotMoveInfoResponse.builder()
                .day(schedule.getDay())
                .build();
        response.infos.addAll(
                schedule.getMoveInfos().stream()
                        .map(SpotMoveInfoDetailResponse::of)
                        .collect(Collectors.toList())
        );
        return response;
    }
}