package com.deploy.bemyplan.plan.service.dto.request;

import com.deploy.bemyplan.domain.plan.TravelTheme;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class GetPlansByThemeRequest {
    private final List<TravelTheme> includes = new ArrayList<>();
    private final List<TravelTheme> excludes = new ArrayList<>();

    public GetPlansByThemeRequest(final List<TravelTheme> includes, final List<TravelTheme> excludes) {
        this.includes.addAll(includes);
        this.excludes.addAll(excludes);
    }
}
