package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.dto.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.service.user.dto.response.UserInfoResponse;
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

    private UserInfoResponse user;
    private boolean scrapStatus;
    private boolean orderStatus;

    @Builder(access = AccessLevel.PRIVATE)
    private PlanInfoResponse(Long planId, String thumbnailUrl, String title, UserInfoResponse user, boolean scrapStatus, boolean orderStatus) {
        this.planId = planId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.user = user;
        this.scrapStatus = scrapStatus;
        this.orderStatus = orderStatus;
    }

    public static PlanInfoResponse of(@NotNull Plan plan, @NotNull User user, boolean scrapStatus, boolean orderStatus) {
        PlanInfoResponse response = new PlanInfoResponse(
                plan.getId(),
                plan.getThumbnailUrl(),
                plan.getTitle(),
                UserInfoResponse.of(user),
                scrapStatus,
                orderStatus);

        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
    }
}