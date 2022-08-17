package com.deploy.bemyplan.service.plan.dto.response;

import com.deploy.bemyplan.common.dto.AuditingTimeResponse;
import com.deploy.bemyplan.domain.common.Money;
import com.deploy.bemyplan.domain.plan.Plan;
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

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanPreviewResponseDto extends AuditingTimeResponse {

    private Long planId;
    private String title;
    private String description;
    private List<String> thumbnail;
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
        final PlanPreviewResponseDto response = new PlanPreviewResponseDto(
                plan.getId(),
                plan.getTitle(),
                plan.getDescription(),
                previewImages,
                plan.getHashtags(),
                plan.getTagInfo().getTheme(),
                plan.getSpotCount(),
                plan.getRestaurantCount(),
                plan.getTagInfo().getTotalDay(),
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
