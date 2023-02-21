package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanDetailResponse extends AuditingTimeResponse {

    private Long planId;
    private String title;
    private String description;

    private CreatorInfoResponse user;

    private List<ScheduleDetailResponse> contents = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private PlanDetailResponse(Long planId, String title, final String description, CreatorInfoResponse creator) {
        this.planId = planId;
        this.title = title;
        this.description = description;
        this.user = creator;
    }

    public static PlanDetailResponse of(@NotNull Plan plan, @NotNull Creator creator) {
        PlanDetailResponse response = new PlanDetailResponse(
                plan.getId(),
                plan.getTitle(),
                plan.getDescription(),
                CreatorInfoResponse.of(creator)
        );

        ScheduleDetailResponse content = ScheduleDetailResponse.of(plan.getSpots());
        response.contents.add(content);

        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
    }
}