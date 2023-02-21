package com.deploy.bemyplan.plan.service.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanScrapResponse {
    private Long planId;
    private String thumbnailUrl;
    private String title;
    private boolean scrapStatus;
    private boolean orderStatus;

    private PlanScrapResponse(final Long planId, final String thumbnailUrl, final String title, final boolean scrapStatus, final boolean orderStatus) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.scrapStatus = scrapStatus;
        this.orderStatus = orderStatus;
    }

    public static PlanScrapResponse of(final Long planId, final String thumbnailUrl, final String title, final boolean scrapStatus, final boolean orderStatus){
        return new PlanScrapResponse(planId, thumbnailUrl, title, scrapStatus, orderStatus);
    }
}
