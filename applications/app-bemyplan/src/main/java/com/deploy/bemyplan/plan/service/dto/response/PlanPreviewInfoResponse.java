package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanPreviewInfoResponse {

    private Long planId;
    private String title;
    private String description;
    private String nickname;
    private int spotCnt;
    private int rstrnCnt;
    private int price;
    private Money budget;
    private int month;
    private int totalDay;
    private TravelTheme theme;
    private TravelPartner partner;
    private TravelMobility mobility;


    public static PlanPreviewInfoResponse of(@NotNull Plan plan, String nickname) {
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
                nickname,
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