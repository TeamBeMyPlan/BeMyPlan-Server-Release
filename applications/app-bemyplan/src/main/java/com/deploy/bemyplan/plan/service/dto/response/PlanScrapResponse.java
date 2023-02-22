package com.deploy.bemyplan.plan.service.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanScrapResponse {
    private Long planId;
    private String thumbnailUrl;
    private String title;
    private boolean scrapStatus;
    private boolean orderStatus;
    private LocalDateTime scrapCreatedAt;

    private PlanScrapResponse(final Long planId, final String thumbnailUrl, final String title, final boolean scrapStatus, final boolean orderStatus, final LocalDateTime scrapCreatedAt) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.scrapStatus = scrapStatus;
        this.orderStatus = orderStatus;
        this.scrapCreatedAt = scrapCreatedAt;
    }

    public static PlanScrapResponse of(final Long planId, final String thumbnailUrl, final String title, final boolean scrapStatus, final boolean orderStatus, final LocalDateTime scrapCreatedAt){
        return new PlanScrapResponse(planId, thumbnailUrl, title, scrapStatus, orderStatus, scrapCreatedAt);
    }
}
