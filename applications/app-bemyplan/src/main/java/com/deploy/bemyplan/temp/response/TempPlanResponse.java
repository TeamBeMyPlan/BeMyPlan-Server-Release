package com.deploy.bemyplan.temp.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TempPlanResponse {

    private Long planId;
    private String title;
    private TempCreatorInfoResponse creator;
    private List<TempScheduleDetailResponse> contents = new ArrayList<>();

    public static TempPlanResponse of(Long planId, String title, TempCreatorInfoResponse creator, List<TempScheduleDetailResponse> contents) {
        return new TempPlanResponse(planId, title, creator, contents);
    }
}
