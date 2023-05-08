package com.deploy.bemyplan.search.service;

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
    private boolean orderStatus;

    private PlanSearchResponse(final Long planId, final String thumbnailUrl, final String title, final CreatorInfoResponse creator, final boolean scrapStatus,
                               final boolean orderStatus) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.creator = creator;
        this.scrapStatus = scrapStatus;
        this.orderStatus = orderStatus;
    }

    public static PlanSearchResponse of(@NotNull Plan plan, @NotNull Creator creator,
                                        boolean scrapStatus,
                                        boolean orderStatus) {
        PlanSearchResponse response = new PlanSearchResponse(
                plan.getId(),
                plan.getThumbnailUrl(),
                plan.getTitle(),
                CreatorInfoResponse.of(creator),
                scrapStatus,
                orderStatus);

        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
    }
}
