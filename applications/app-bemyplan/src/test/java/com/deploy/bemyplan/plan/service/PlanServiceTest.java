package com.deploy.bemyplan.plan.service;

import com.deploy.bemyplan.common.exception.model.NotFoundException;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewContentStatus;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.domain.user.User;
import com.deploy.bemyplan.domain.user.UserRepository;
import com.deploy.bemyplan.domain.user.UserSocialType;
import com.deploy.bemyplan.plan.service.dto.response.CreatorPlanResponse;
import com.deploy.bemyplan.plan.service.dto.response.PlanPreviewResponseDto;
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

class PlanServiceTest {
    private PlanService planService;
    private PlanRepository spyPlanRepository;
    private PreviewRepository spyPreviewRepository;
    private UserRepository spyUserRepository;


    @BeforeEach
    void setUp() {
        spyPlanRepository = mock(PlanRepository.class);
        spyPreviewRepository = mock(PreviewRepository.class);
        spyUserRepository = mock(UserRepository.class);

        planService = new PlanService(spyPreviewRepository, spyPlanRepository, null, spyUserRepository);
    }

    @Test
    void getPlanPreview_callsPlanFromRepository() {
        Plan givenPlan = newInstance(1L, RegionCategory.JEJU, Region.JEJUALL, "thumbnailUrl", "title", "description", TagInfo.testBuilder().build(), 10000, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        Preview givenPreview = Preview.newInstance(givenPlan, List.of("img1.png"), "", PreviewContentStatus.ACTIVE, 1L);
        given(spyPlanRepository.findById(any())).willReturn(Optional.of(givenPlan));
        given(spyPreviewRepository.findAllPreviewByPlanId(givenPlan.getId())).willReturn(List.of(givenPreview));

        planService.getPlanPreview(givenPlan.getId());

        verify(spyPlanRepository, times(1)).findById(givenPlan.getId());
    }

    @Test
    void getPlanPreview_callsPreviewFromRepository() {
        Plan givenPlan = newInstance(1L, RegionCategory.JEJU, Region.JEJUALL, "", "", "", TagInfo.testBuilder().build(), 0, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        Preview givenPreview = Preview.newInstance(givenPlan, List.of("image.png"), "description", PreviewContentStatus.ACTIVE, 1L);
        given(spyPlanRepository.findById(any())).willReturn(Optional.of(givenPlan));
        given(spyPreviewRepository.findAllPreviewByPlanId(givenPlan.getId())).willReturn(List.of(givenPreview));

        planService.getPlanPreview(givenPlan.getId());

        verify(spyPreviewRepository, times(1)).findAllPreviewByPlanId(givenPlan.getId());
    }

    @Test
    void getPlanPreview_returnsPlanPreview() {
        Plan givenPlan = newInstance(1L, RegionCategory.JEJU, Region.JEJUALL, "", "", "", TagInfo.testBuilder().build(), 0, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        Preview givenPreview1 = Preview.newInstance(givenPlan, List.of("image.png", "image2.png"), "description", PreviewContentStatus.ACTIVE, 1L);
        Preview givenPreview2 = Preview.newInstance(givenPlan, List.of("2ndImage.png", "image2.png"), "description", PreviewContentStatus.ACTIVE, 1L);
        given(spyPlanRepository.findById(any())).willReturn(Optional.of(givenPlan));
        given(spyPreviewRepository.findAllPreviewByPlanId(givenPlan.getId())).willReturn(List.of(givenPreview1, givenPreview2));

        PlanPreviewResponseDto result = planService.getPlanPreview(givenPlan.getId());

        assertThat(result.getPlanId()).isEqualTo(givenPlan.getId());
        assertThat(result.getTitle()).isEqualTo(givenPlan.getTitle());
        assertThat(result.getDescription()).isEqualTo(givenPlan.getDescription());
        assertThat(result.getThumbnail()).isEqualTo(List.of(givenPreview1.getImageUrls().get(0), givenPreview2.getImageUrls().get(0)));
        assertThat(result.getHashtag()).isEqualTo(givenPlan.getHashtags());
        assertThat(result.getTheme()).isEqualTo(givenPlan.getTagInfo().getTheme());
        assertThat(result.getSpotCount()).isEqualTo(0);
        assertThat(result.getRestaurantCount()).isEqualTo(0);
        assertThat(result.getTotalDay()).isEqualTo(givenPlan.getTagInfo().getTotalDay());
        assertThat(result.getTravelPartner()).isEqualTo(givenPlan.getTagInfo().getPartner());
        assertThat(result.getBudget()).isEqualTo(givenPlan.getTagInfo().getBudget());
        assertThat(result.getTravelMobility()).isEqualTo(givenPlan.getTagInfo().getMobility());
        assertThat(result.getMonth()).isEqualTo(givenPlan.getTagInfo().getMonth());
        assertThat(result.getPrice()).isEqualTo(givenPlan.getPrice());
        assertThat(result.getRecommendTarget()).isEqualTo(givenPlan.getRecommendTargets());
    }

    @Test
    void getPlanPreview_returnsNotFoundExceptionWhenPlanNotExists() {

        assertThatThrownBy(() -> planService.getPlanPreview(1L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void getCreatorPlansCallsUserRepository() {
        given(spyUserRepository.findCreatorIdByUserId(any())).willReturn(Optional.of(1L));

        planService.getCreatorPlans(1L);

        verify(spyUserRepository, times(1)).findCreatorIdByUserId(1L);
    }

    @Test
    void getCreatorPlansThrowsNotFoundExceptionWhenNotCreator() {
        assertThatThrownBy(() -> planService.getCreatorPlans(1L))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void getCreatorPlansPassesCreatorIdToRepository() {
        User givenUser = User.newInstance("socialId", UserSocialType.APPLE, "name", "email");
        givenUser.connectCreatorAccount(1L);
        given(spyUserRepository.findCreatorIdByUserId(any())).willReturn(Optional.of(givenUser.getCreatorId()));

        planService.getCreatorPlans(1L);

        verify(spyPlanRepository, times(1)).findAllByUserId(givenUser.getCreatorId());
    }

    @Test
    void getCreatorPlansReturnsCreatorPlans() {
        User givenUser = User.newInstance("socialId", UserSocialType.APPLE, "name", "email");
        givenUser.connectCreatorAccount(1L);
        Plan givenPlan = newInstance(givenUser.getCreatorId(), RegionCategory.JEJU, Region.JEJUALL, "", "", "", TagInfo.testBuilder().build(), 0, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        given(spyUserRepository.findCreatorIdByUserId(any())).willReturn(Optional.of(givenUser.getCreatorId()));
        given(spyPlanRepository.findAllByUserId(any())).willReturn(List.of(givenPlan));

        List<CreatorPlanResponse> result = planService.getCreatorPlans(givenUser.getId());

        assertThat(result.get(0).getPlanId()).isEqualTo(givenPlan.getId());
        assertThat(result.get(0).getTitle()).isEqualTo(givenPlan.getTitle());
        assertThat(result.get(0).getThumbnailUrl()).isEqualTo(givenPlan.getThumbnailUrl());
        assertThat(result.get(0).getRegion()).isEqualTo(givenPlan.getRegion());
    }
}