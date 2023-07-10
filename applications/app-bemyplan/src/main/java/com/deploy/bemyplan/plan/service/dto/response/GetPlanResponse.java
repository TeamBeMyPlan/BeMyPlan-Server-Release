package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.user.service.dto.response.CreatorInfoResponse;

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

    public GetPlanResponse(Long planId, String thumbnailUrl, String title, Region region, CreatorInfoResponse creator, boolean scrapStatus, boolean orderStatus) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.region = region;
        this.creator = creator;
        this.scrapStatus = scrapStatus;
        this.orderStatus = orderStatus;
    }

    public Long getPlanId() {
        return this.planId;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public Region getRegion() {
        return this.region;
    }

    public CreatorInfoResponse getCreator() {
        return this.creator;
    }

    public boolean isScrapStatus() {
        return this.scrapStatus;
    }

    public boolean isOrderStatus() {
        return this.orderStatus;
    }
}
