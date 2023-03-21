package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Plan;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanResponse extends AuditingTimeResponse {

    private Long planId;
    private String thumbnailUrl;
    private String title;

    private PlanResponse(final Long planId, final String thumbnailUrl, final String title) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
    }

    public static PlanResponse of(@NotNull final Plan plan){
        PlanResponse response = new PlanResponse(
                plan.getId(),
                plan.getThumbnailUrl(),
                plan.getTitle()
        );
        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
    }
}
