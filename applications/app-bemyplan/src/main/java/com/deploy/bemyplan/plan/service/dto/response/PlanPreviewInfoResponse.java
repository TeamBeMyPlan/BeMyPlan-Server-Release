package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.domain.plan.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotCategoryType;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

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

    private PlanPreviewInfoResponse(Long planId, String title, String description, String nickname, int spotCnt, int rstrnCnt, int price, Money budget, int month, int totalDay, TravelTheme theme, TravelPartner partner, TravelMobility mobility) {
        this.planId = planId;
        this.title = title;
        this.description = description;
        this.nickname = nickname;
        this.spotCnt = spotCnt;
        this.rstrnCnt = rstrnCnt;
        this.price = price;
        this.budget = budget;
        this.month = month;
        this.totalDay = totalDay;
        this.theme = theme;
        this.partner = partner;
        this.mobility = mobility;
    }

    private PlanPreviewInfoResponse() {
    }


    public static PlanPreviewInfoResponse of(@NotNull Plan plan, String nickname) {

        int spotCnt = (int) plan.getSpots().stream().count();

        int rstrnCnt = (int) plan.getSpots().stream()
                .filter(spot -> spot.getCategory().equals(SpotCategoryType.RESTAURANT))
                .count();

        final int totalDays = plan.getSpots().stream()
                .mapToInt(Spot::getDay)
                .boxed()
                .collect(Collectors.toSet())
                .size();

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
                totalDays,
                plan.getTagInfo().getTheme(),
                plan.getTagInfo().getPartner(),
                plan.getTagInfo().getMobility()
        );
    }

    public Long getPlanId() {
        return this.planId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getNickname() {
        return this.nickname;
    }

    public int getSpotCnt() {
        return this.spotCnt;
    }

    public int getRstrnCnt() {
        return this.rstrnCnt;
    }

    public int getPrice() {
        return this.price;
    }

    public Money getBudget() {
        return this.budget;
    }

    public int getMonth() {
        return this.month;
    }

    public int getTotalDay() {
        return this.totalDay;
    }

    public TravelTheme getTheme() {
        return this.theme;
    }

    public TravelPartner getPartner() {
        return this.partner;
    }

    public TravelMobility getMobility() {
        return this.mobility;
    }

    public String toString() {
        return "PlanPreviewInfoResponse(planId=" + this.getPlanId() + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", nickname=" + this.getNickname() + ", spotCnt=" + this.getSpotCnt() + ", rstrnCnt=" + this.getRstrnCnt() + ", price=" + this.getPrice() + ", budget=" + this.getBudget() + ", month=" + this.getMonth() + ", totalDay=" + this.getTotalDay() + ", theme=" + this.getTheme() + ", partner=" + this.getPartner() + ", mobility=" + this.getMobility() + ")";
    }
}