package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.domain.common.Money;
import com.deploy.bemyplan.domain.plan.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanPreviewInfoResponse {

    private Long planId;
    private String title;
    private String description;
    private int spotCnt;
    private int rstrnCnt;
    private int price;
    private Money budget;
    private int month;
    private int totalDay;
    private TravelTheme theme;
    private TravelPartner partner;
    private TravelMobility mobility;

    public static PlanPreviewInfoResponse of(@NotNull Plan plan) {
        int spotCnt = (int) plan.getSchedules().stream()
                .flatMap(dailySchedule -> dailySchedule.getSpots().stream())
                .count();

        int rstrnCnt = (int) plan.getSchedules().stream()
                .flatMap(dailySchedule -> dailySchedule.getSpots().stream())
                .filter(spot -> spot.getCategory().equals(SpotCategoryType.RESTAURANT))
                .count();

        return new PlanPreviewInfoResponse(
                plan.getId(),
                plan.getTitle(),
                plan.getDescription(),
                spotCnt,
                rstrnCnt,
                plan.getPrice(),
                plan.getTagInfo().getBudget(),
                plan.getTagInfo().getMonth(),
                plan.getTagInfo().getTotalDay(),
                plan.getTagInfo().getTheme(),
                plan.getTagInfo().getPartner(),
                plan.getTagInfo().getMobility()
        );
    }
}