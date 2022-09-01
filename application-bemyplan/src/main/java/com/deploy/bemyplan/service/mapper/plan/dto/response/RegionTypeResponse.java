package com.deploy.bemyplan.service.mapper.plan.dto.response;

import com.deploy.bemyplan.domain.plan.RegionCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionTypeResponse {

    private RegionCategory region;
    private String name;
    private String thumbnailUrl;
    private boolean isLocked;

    public static RegionTypeResponse of(RegionCategory region) {
        return new RegionTypeResponse(
                region,
                region.getRegionCategoryName(),
                region.getThumbnailUrl(),
                region.isLocked()
        );
    }
}