package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanDetailResponse extends AuditingTimeResponse {

    private Long planId;
    private String title;
    private String description;

    private CreatorInfoResponse creator;

    private List<ScheduleDetailResponse> contents = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private PlanDetailResponse(Long planId, String title, final String description, CreatorInfoResponse creator) {
        this.planId = planId;
        this.title = title;
        this.description = description;
        this.creator = creator;
    }

    public static PlanDetailResponse of(@NotNull Plan plan, @NotNull Creator creator) {
        PlanDetailResponse response = new PlanDetailResponse(
                plan.getId(),
                plan.getTitle(),
                plan.getDescription(),
                CreatorInfoResponse.of(creator)
        );

        Map<Integer, List<Spot>> spotsByDay = plan.getSpots().stream()
                .collect(Collectors.groupingBy(Spot::getDay));

        spotsByDay.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .forEach(spots -> response.contents.add(ScheduleDetailResponse.of(spots)));

        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
    }
}