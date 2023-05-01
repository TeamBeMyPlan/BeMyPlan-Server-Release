package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanSearchResponse extends AuditingTimeResponse {

    private Long planId;
    private String thumbnailUrl;
    private String title;
    private CreatorInfoResponse creator;
    private boolean scrapStatus;

    private PlanSearchResponse(final Long planId, final String thumbnailUrl, final String title, final CreatorInfoResponse creator, final boolean scrapStatus) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.creator = creator;
        this.scrapStatus = scrapStatus;
    }

    public static PlanSearchResponse of(@NotNull Plan plan, @NotNull Creator creator, boolean scrapStatus) {
        PlanSearchResponse response = new PlanSearchResponse(
                plan.getId(),
                plan.getThumbnailUrl(),
                plan.getTitle(),
                CreatorInfoResponse.of(creator),
                scrapStatus);

        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
    }
}
