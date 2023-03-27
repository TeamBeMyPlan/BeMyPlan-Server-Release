package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.domain.plan.RegionCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RetrievePlansRequest {
    private RegionCategory region;
    private String sort = "createdAt";
}