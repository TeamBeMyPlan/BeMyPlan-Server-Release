package com.deploy.bemyplan.controller.plan.dto.request;

import com.deploy.bemyplan.domain.plan.RegionType;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrievePlansRequest {

    @Nullable
    private RegionType region;

    @Min(value = 1, message = "{common.size.min}")
    @Max(value = 100, message = "{common.size.max}")
    private int size;

    @Nullable
    private Long authorId;

    @Nullable
    private Long lastPlanId;
}