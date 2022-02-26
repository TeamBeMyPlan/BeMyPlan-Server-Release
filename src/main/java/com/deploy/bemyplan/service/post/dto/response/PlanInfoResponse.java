package com.deploy.bemyplan.service.post.dto.response;

import com.deploy.bemyplan.common.dto.AuditingTimeResponse;
import com.deploy.bemyplan.controller.user.dto.response.UserInfoResponse;
import com.deploy.bemyplan.domain.post.Post;
import com.deploy.bemyplan.domain.user.User;
import lombok.*;
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

    public static PlanInfoResponse of(@NotNull Post plan, @NotNull User user, boolean scrapStatus, boolean orderStatus) {
        return PlanInfoResponse.builder()
                .planId(plan.getId())
                .thumbnailUrl(plan.getThumbnailUrl())
                .title(plan.getTitle())
                .user(UserInfoResponse.of(user))
                .scrapStatus(scrapStatus)
                .orderStatus(orderStatus)
                .build();
    }
}