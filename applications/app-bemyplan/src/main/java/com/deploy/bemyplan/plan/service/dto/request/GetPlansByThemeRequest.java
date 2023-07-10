package com.deploy.bemyplan.plan.service.dto.request;

import com.deploy.bemyplan.domain.plan.TravelTheme;

import java.util.ArrayList;
import java.util.List;

public class GetPlansByThemeRequest {
    private final Long userId;
    private final List<TravelTheme> includes = new ArrayList<>();
    private final List<TravelTheme> excludes = new ArrayList<>();

    public GetPlansByThemeRequest(final Long userId, final List<TravelTheme> includes, final List<TravelTheme> excludes) {
        this.userId = userId;
        this.includes.addAll(includes);
        this.excludes.addAll(excludes);
    }

    public Long getUserId() {
        return this.userId;
    }

    public List<TravelTheme> getIncludes() {
        return this.includes;
    }

    public List<TravelTheme> getExcludes() {
        return this.excludes;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GetPlansByThemeRequest)) return false;
        final GetPlansByThemeRequest other = (GetPlansByThemeRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$includes = this.getIncludes();
        final Object other$includes = other.getIncludes();
        if (this$includes == null ? other$includes != null : !this$includes.equals(other$includes)) return false;
        final Object this$excludes = this.getExcludes();
        final Object other$excludes = other.getExcludes();
        if (this$excludes == null ? other$excludes != null : !this$excludes.equals(other$excludes)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GetPlansByThemeRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $includes = this.getIncludes();
        result = result * PRIME + ($includes == null ? 43 : $includes.hashCode());
        final Object $excludes = this.getExcludes();
        result = result * PRIME + ($excludes == null ? 43 : $excludes.hashCode());
        return result;
    }
}
