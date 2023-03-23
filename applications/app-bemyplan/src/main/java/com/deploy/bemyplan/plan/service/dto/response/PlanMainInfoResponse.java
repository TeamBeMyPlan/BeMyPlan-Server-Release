package com.deploy.bemyplan.plan.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanMainInfoResponse {
    private Long planId;
    private String thumbnailUrl;
    private String title;
    private boolean scrapStatus;
    private boolean orderStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private PlanMainInfoResponse(final Long planId, final String thumbnailUrl, final String title, final boolean scrapStatus, final boolean orderStatus, final LocalDateTime createdAt) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.scrapStatus = scrapStatus;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
    }

    public static PlanMainInfoResponse of(final Long planId, final String thumbnailUrl, final String title, final boolean scrapStatus, final boolean orderStatus, final LocalDateTime createdAt) {
        return new PlanMainInfoResponse(planId, thumbnailUrl, title, scrapStatus, orderStatus, createdAt);
    }
}
