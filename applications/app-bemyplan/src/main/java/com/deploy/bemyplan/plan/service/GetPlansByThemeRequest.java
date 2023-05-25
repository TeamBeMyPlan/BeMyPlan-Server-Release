package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.domain.plan.TravelTheme;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class GetPlansByThemeRequest {
    private final List<TravelTheme> includes = new ArrayList<>();
    private final List<TravelTheme> excludes = new ArrayList<>();

    public GetPlansByThemeRequest(final List<TravelTheme> includes, final List<TravelTheme> excludes) {
        this.includes.addAll(includes);
        this.excludes.addAll(excludes);
    }
}
