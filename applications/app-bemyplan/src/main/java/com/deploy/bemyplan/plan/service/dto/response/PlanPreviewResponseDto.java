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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
}
