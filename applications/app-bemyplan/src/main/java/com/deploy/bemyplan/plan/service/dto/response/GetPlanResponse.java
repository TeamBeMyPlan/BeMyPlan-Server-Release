package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPlanResponse {
    private Long planId;
    private String thumbnailUrl;
    private String title;
    private Region region;
    private CreatorInfoResponse creator;
    private boolean scrapStatus;
    private boolean orderStatus;

    public GetPlanResponse(Plan plan, Creator creator, boolean scrapStatus, boolean orderStatus) {
        this(plan.getId(),
                plan.getThumbnailUrl(),
                plan.getTitle(),
                plan.getRegion(),
                CreatorInfoResponse.of(creator),
                scrapStatus,
                orderStatus
        );
    }
}
