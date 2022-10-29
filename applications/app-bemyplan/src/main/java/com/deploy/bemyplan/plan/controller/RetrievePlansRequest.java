package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.domain.plan.RegionCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrievePlansRequest {
    @Nullable
    private RegionCategory region;

}