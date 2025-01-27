package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanInfoResponse extends AuditingTimeResponse {

    private Long planId;
    private String thumbnailUrl;
    private String title;
    private Region region;

    private CreatorInfoResponse creator;
    private boolean scrapStatus;
    private boolean orderStatus;

    @Builder(access = AccessLevel.PRIVATE)
    private PlanInfoResponse(Long planId, String thumbnailUrl, String title, Region region, CreatorInfoResponse creator, boolean scrapStatus, boolean orderStatus) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.region = region;
        this.creator = creator;
        this.scrapStatus = scrapStatus;
        this.orderStatus = orderStatus;
    }

    public static PlanInfoResponse of(@NotNull Plan plan, @NotNull Creator creator, boolean scrapStatus, boolean orderStatus) {
        PlanInfoResponse response = new PlanInfoResponse(
                plan.getId(),
                plan.getThumbnailUrl(),
                plan.getTitle(),
                plan.getRegion(),
                CreatorInfoResponse.of(creator),
                scrapStatus,
                orderStatus);

        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
    }
}