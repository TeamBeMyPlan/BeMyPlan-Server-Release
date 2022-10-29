package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.dto.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.DailySchedule;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.user.service.dto.response.UserInfoResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanDetailResponse extends AuditingTimeResponse {

    private Long planId;
    private String title;

    private UserInfoResponse user;

    private List<ScheduleDetailResponse> contents = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    private PlanDetailResponse(Long planId, String title, UserInfoResponse user) {
        this.planId = planId;
        this.title = title;
        this.user = user;
    }

    public static PlanDetailResponse of(@NotNull Plan plan, @NotNull User user) {
        PlanDetailResponse response = PlanDetailResponse.builder()
                .planId(plan.getId())
                .title(plan.getTitle())
                .user(UserInfoResponse.of(user))
                .build();

        for (DailySchedule schedule : plan.getSchedules()) {
            ScheduleDetailResponse content = ScheduleDetailResponse.of(schedule.getSpots());
            response.contents.add(content);
        }
        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
    }
}
