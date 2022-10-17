package com.deploy.bemyplan.controller.temp.response;


import com.deploy.bemyplan.service.plan.dto.response.ScheduleDetailResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TempPlanResponse {

    private Long planId;
    private String title;
    private TempUserInfoResponse user;
    private List<TempScheduleDetailResponse> contents = new ArrayList<>();

    public static TempPlanResponse of (Long planId, String title, TempUserInfoResponse user, List<TempScheduleDetailResponse> contents){
        return new TempPlanResponse(planId, title, user, contents);
    }
}
