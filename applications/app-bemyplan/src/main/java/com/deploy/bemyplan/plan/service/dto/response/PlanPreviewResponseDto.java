package com.deploy.bemyplan.plan.service.dto.response;

import com.deploy.bemyplan.common.controller.AuditingTimeResponse;
import com.deploy.bemyplan.domain.plan.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class PlanPreviewResponseDto extends AuditingTimeResponse {

    private Long planId;
    private String title;
    private String description;
    private List<String> thumbnail;
    private RegionCategory regionCategory;
    private Region region;
    private List<String> hashtag;
    private TravelTheme theme;
    private int spotCount;
    private int restaurantCount;
    private int totalDay;
    private TravelPartner travelPartner;
    private Money budget;
    private TravelMobility travelMobility;
    private int month;
    private int price;
    private List<String> recommendTarget;

    private PlanPreviewResponseDto(Long planId, String title, String description, List<String> thumbnail, RegionCategory regionCategory, Region region, List<String> hashtag, TravelTheme theme, int spotCount, int restaurantCount, int totalDay, TravelPartner travelPartner, Money budget, TravelMobility travelMobility, int month, int price, List<String> recommendTarget) {
        this.planId = planId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.regionCategory = regionCategory;
        this.region = region;
        this.hashtag = hashtag;
        this.theme = theme;
        this.spotCount = spotCount;
        this.restaurantCount = restaurantCount;
        this.totalDay = totalDay;
        this.travelPartner = travelPartner;
        this.budget = budget;
        this.travelMobility = travelMobility;
        this.month = month;
        this.price = price;
        this.recommendTarget = recommendTarget;
    }

    private PlanPreviewResponseDto() {
    }

    public static PlanPreviewResponseDto of(@NotNull final Plan plan, final List<String> previewImages) {
        final int totalDays = plan.getSpots().stream()
                .mapToInt(Spot::getDay)
                .boxed()
                .collect(Collectors.toSet())
                .size();

        final PlanPreviewResponseDto response = new PlanPreviewResponseDto(
                plan.getId(),
                plan.getTitle(),
                plan.getDescription(),
                previewImages,
                plan.getRegionCategory(),
                plan.getRegion(),
                plan.getHashtags(),
                plan.getTagInfo().getTheme(),
                plan.getSpotCount(),
                plan.getRestaurantCount(),
                totalDays,
                plan.getTagInfo().getPartner(),
                plan.getTagInfo().getBudget(),
                plan.getTagInfo().getMobility(),
                plan.getTagInfo().getMonth(),
                plan.getPrice(),
                plan.getRecommendTargets()
        );
        response.setBaseTime(plan.getCreatedAt(), plan.getUpdatedAt());
        return response;
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

    public List<String> getThumbnail() {
        return this.thumbnail;
    }

    public RegionCategory getRegionCategory() {
        return this.regionCategory;
    }

    public Region getRegion() {
        return this.region;
    }

    public List<String> getHashtag() {
        return this.hashtag;
    }

    public TravelTheme getTheme() {
        return this.theme;
    }

    public int getSpotCount() {
        return this.spotCount;
    }

    public int getRestaurantCount() {
        return this.restaurantCount;
    }

    public int getTotalDay() {
        return this.totalDay;
    }

    public TravelPartner getTravelPartner() {
        return this.travelPartner;
    }

    public Money getBudget() {
        return this.budget;
    }

    public TravelMobility getTravelMobility() {
        return this.travelMobility;
    }

    public int getMonth() {
        return this.month;
    }

    public int getPrice() {
        return this.price;
    }

    public List<String> getRecommendTarget() {
        return this.recommendTarget;
    }

    public String toString() {
        return "PlanPreviewResponseDto(planId=" + this.getPlanId() + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", thumbnail=" + this.getThumbnail() + ", regionCategory=" + this.getRegionCategory() + ", region=" + this.getRegion() + ", hashtag=" + this.getHashtag() + ", theme=" + this.getTheme() + ", spotCount=" + this.getSpotCount() + ", restaurantCount=" + this.getRestaurantCount() + ", totalDay=" + this.getTotalDay() + ", travelPartner=" + this.getTravelPartner() + ", budget=" + this.getBudget() + ", travelMobility=" + this.getTravelMobility() + ", month=" + this.getMonth() + ", price=" + this.getPrice() + ", recommendTarget=" + this.getRecommendTarget() + ")";
    }
}
