package com.deploy.bemyplan.service.plan;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewContentStatus;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.RegionType;
import com.deploy.bemyplan.domain.plan.TagInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.deploy.bemyplan.domain.plan.Plan.newInstance;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PlanServiceTest {
    private PlanService planService;
    private PlanRepository stubPlanRepository;
    private PreviewRepository stubPreviewRepository;


    @BeforeEach
    void setUp() {
        stubPlanRepository = mock(PlanRepository.class);
        stubPreviewRepository = mock(PreviewRepository.class);

        planService = new PlanService(stubPlanRepository, stubPreviewRepository);
    }

//    @Test
//    void getPlanPreview_passesPlanFromRepository() {
//        Plan givenPlan = newInstance(1L, RegionType.JEJU, "thumbnailUrl", "title", "description", TagInfo.testBuilder().build(), 10000, PlanStatus.ACTIVE, RcmndStatus.NONE);
//        given(stubPlanRepository.findById(any())).willReturn(Optional.of(givenPlan));
//
//        planService.getPlanPreview(1L);
//
//        verify(stubPlanRepository, times(1)).findById(1L);
//    }

    @Test
    void getPlanPreview_passesPreviewFromRepository() {
        Plan givenPlan = newInstance(1L, RegionType.JEJU, "thumbnailUrl", "title", "description", TagInfo.testBuilder().build(), 10000, PlanStatus.ACTIVE, RcmndStatus.NONE);
        Preview givenPreview = Preview.newInstance(givenPlan, "image.png", "description", PreviewContentStatus.ACTIVE);
        given(stubPlanRepository.findById(any())).willReturn(Optional.of(givenPlan));
        given(stubPreviewRepository.findPreviewByPlanId(givenPlan.getId())).willReturn(Optional.of(givenPreview));

        planService.getPlanPreview(1L);

        verify(stubPreviewRepository, times(1)).findPreviewByPlanId(1L);
    }


}