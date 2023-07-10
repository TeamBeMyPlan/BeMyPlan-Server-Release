package com.deploy.bemyplan.temp.response;


import java.util.ArrayList;
import java.util.List;

public class TempPlanResponse {

    private Long planId;
    private String title;
    private TempCreatorInfoResponse creator;
    private List<TempScheduleDetailResponse> contents = new ArrayList<>();

    private TempPlanResponse(Long planId, String title, TempCreatorInfoResponse creator, List<TempScheduleDetailResponse> contents) {
        this.planId = planId;
        this.title = title;
        this.creator = creator;
        this.contents = contents;
    }

    private TempPlanResponse() {
    }

    public static TempPlanResponse of(Long planId, String title, TempCreatorInfoResponse creator, List<TempScheduleDetailResponse> contents) {
        return new TempPlanResponse(planId, title, creator, contents);
    }

    public Long getPlanId() {
        return this.planId;
    }

    public String getTitle() {
        return this.title;
    }

    public TempCreatorInfoResponse getCreator() {
        return this.creator;
    }

    public List<TempScheduleDetailResponse> getContents() {
        return this.contents;
    }

    public String toString() {
        return "TempPlanResponse(planId=" + this.getPlanId() + ", title=" + this.getTitle() + ", creator=" + this.getCreator() + ", contents=" + this.getContents() + ")";
    }
}
