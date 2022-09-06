package com.deploy.bemyplan.service.mapper.plan;

import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.service.mapper.plan.dto.response.RegionTypeResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegionTypeMapper {

    private static final RegionTypeMapper INSTANCE = new RegionTypeMapper();

    public static RegionTypeMapper getInstance() {
        return INSTANCE;
    }

    public List<RegionTypeResponse> getActiveRegionTypes() {
        return Arrays.stream(RegionCategory.values())
//                .filter(r -> !r.isLocked())
                .sorted(new Comparator<RegionCategory>() {
                    @Override
                    public int compare(RegionCategory o1, RegionCategory o2) {
                        return o1.getDisplayOrder() - o2.getDisplayOrder();
                    }
                })
                .map(region -> RegionTypeResponse.of(region))
                .collect(Collectors.toList());
    }
}
