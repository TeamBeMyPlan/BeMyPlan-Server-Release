package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.RegionType;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.service.plan.PlanService;
import com.deploy.bemyplan.service.plan.dto.response.PlanPreviewResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static com.deploy.bemyplan.domain.plan.Plan.newInstance;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlanControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PlanService stubPlanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new PlanController(stubPlanService))
                .build();
    }

    @Test
    void getPlanPreviewReturnsOkHttpStatus() throws Exception {
        mockMvc.perform(get("/api/v2/plan/1/preview"))
                .andExpect(status().isOk());
    }

    @Test
    void getPlanPreviewPassesPlanIdToService() throws Exception {
        mockMvc.perform(get("/api/v2/plan/1/preview"));

        verify(stubPlanService, times(1)).getPlanPreview(1L);
    }

    @Test
    void getPlanPreviewReturnsResponse() throws Exception {
        Plan givenPlan = newInstance(1L, RegionType.JEJU, "thumbnail", "title", "description", TagInfo.testBuilder().build(), 2000, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        PlanPreviewResponseDto response = PlanPreviewResponseDto.of(givenPlan, List.of("image.png", "image2.png"));
        given(stubPlanService.getPlanPreview(any())).willReturn(response);

        mockMvc.perform(get("/api/v2/plan/{planId}/preview", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.planId", equalTo(response.getPlanId())))
                .andExpect(jsonPath("$.data.title", equalTo(response.getTitle())))
                .andExpect(jsonPath("$.data.description", equalTo(response.getDescription())))
                .andExpect(jsonPath("$.data.price", equalTo(response.getPrice())))
                .andExpect(jsonPath("$.data.month", equalTo(response.getMonth())))
                .andExpect(jsonPath("$.data.totalDay", equalTo(response.getTotalDay())))
                .andExpect(jsonPath("$.data.theme", equalTo(response.getTheme())))
                .andExpect(jsonPath("$.data.budget", equalTo(response.getBudget())))
                .andExpect(jsonPath("$.data.hashtag", equalTo(response.getHashtag())))
                .andExpect(jsonPath("$.data.recommendTarget", equalTo(response.getRecommendTarget())))
                .andExpect(jsonPath("$.data.travelMobility", equalTo(response.getTravelMobility())))
                .andExpect(jsonPath("$.data.restaurantCount", equalTo(response.getRestaurantCount())))
                .andExpect(jsonPath("$.data.spotCount", equalTo(response.getSpotCount())))
                .andExpect(jsonPath("$.data.thumbnail", equalTo(response.getThumbnail())))
                .andExpect(jsonPath("$.data.travelPartner", equalTo(response.getTravelPartner())));
    }
}