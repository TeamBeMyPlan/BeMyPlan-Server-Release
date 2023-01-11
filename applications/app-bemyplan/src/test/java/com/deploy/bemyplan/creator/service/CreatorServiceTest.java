package com.deploy.bemyplan.creator.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.domain.user.Creator;
import com.deploy.bemyplan.domain.user.CreatorRepository;
import com.deploy.bemyplan.plan.service.dto.response.CreatorPlanResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.deploy.bemyplan.domain.plan.Plan.newInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreatorServiceTest {
    private CreatorService creatorService;
    private PlanRepository spyPlanRepository;
    private CreatorRepository spyCreatorRepository;

    @BeforeEach
    void setUp() {
        spyPlanRepository = mock(PlanRepository.class);
        spyCreatorRepository = mock(CreatorRepository.class);

        creatorService = new CreatorService(spyCreatorRepository, spyPlanRepository);
    }

    @Test
    void getCreatorPlansCallsUserRepository() {
        Creator givenCreator = Creator.newInstance("description", "profileImage", "name");
        givenCreator.connectUserAccount(1L);
        given(spyCreatorRepository.findByUserId(any())).willReturn(Optional.of(givenCreator));

        creatorService.getCreatorPlans(1L);

        verify(spyCreatorRepository, times(1)).findByUserId(1L);
    }

    @Test
    void getCreatorPlansThrowsNotFoundExceptionWhenNotCreator() {
        assertThatThrownBy(() -> creatorService.getCreatorPlans(1L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void getCreatorPlansPassesCreatorIdToRepository() {
        Creator givenCreator = Creator.newInstance("description", "profileImage", "name");
        givenCreator.connectUserAccount(1L);
        given(spyCreatorRepository.findByUserId(any())).willReturn(Optional.of(givenCreator));

        creatorService.getCreatorPlans(1L);

        verify(spyPlanRepository, times(1)).findAllByCreatorId(givenCreator.getId());
    }

    @Test
    void getCreatorPlansReturnsCreatorPlans() {
        Creator givenCreator = Creator.newInstance("description", "profileImage", "name");
        givenCreator.connectUserAccount(1L);
        Plan givenPlan = newInstance(givenCreator.getId(), RegionCategory.JEJU, Region.JEJUALL, "", "", "", TagInfo.testBuilder().build(), 0, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        given(spyCreatorRepository.findByUserId(any())).willReturn(Optional.of(givenCreator));
        given(spyPlanRepository.findAllByCreatorId(givenPlan.getCreatorId())).willReturn(List.of(givenPlan));

        List<CreatorPlanResponse> result = creatorService.getCreatorPlans(givenCreator.getId());

        assertThat(result.get(0).getPlanId()).isEqualTo(givenPlan.getId());
        assertThat(result.get(0).getTitle()).isEqualTo(givenPlan.getTitle());
        assertThat(result.get(0).getThumbnailUrl()).isEqualTo(givenPlan.getThumbnailUrl());
        assertThat(result.get(0).getRegion()).isEqualTo(givenPlan.getRegion());
    }
}