package com.deploy.bemyplan.service.mapper.plan.dto.response;

import com.deploy.bemyplan.domain.plan.RegionType;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionTypeResponse {

    private RegionType region;
    private String name;
    private String thumbnailUrl;
    private boolean isLocked;

    public static RegionTypeResponse of(RegionType region) {
        return new RegionTypeResponse(
                region,
                region.getRegionName(),
                region.getThumbnailUrl(),
                region.isLocked()
        );
    }
}