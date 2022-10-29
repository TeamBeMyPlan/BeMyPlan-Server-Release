package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.plan.service.PlanService;
import com.deploy.bemyplan.plan.service.dto.response.PlanPreviewResponseDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static com.deploy.bemyplan.domain.plan.Plan.newInstance;

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
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/plans/1/preview"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getPlanPreviewPassesPlanIdToService() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/plans/1/preview"));

        Mockito.verify(stubPlanService, Mockito.times(1)).getPlanPreview(1L);
    }

    @Test
    void getPlanPreviewReturnsResponse() throws Exception {
        Plan givenPlan = newInstance(1L, RegionCategory.JEJU, Region.JEJUALL, "thumbnail", "title", "description", TagInfo.testBuilder().build(), 2000, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        PlanPreviewResponseDto response = PlanPreviewResponseDto.of(givenPlan, List.of("image.png", "image2.png"));
        BDDMockito.given(stubPlanService.getPlanPreview(ArgumentMatchers.any())).willReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/plans/{planId}/preview", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.planId", Matchers.equalTo(response.getPlanId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo(response.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.equalTo(response.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.equalTo(response.getPrice())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.month", Matchers.equalTo(response.getMonth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalDay", Matchers.equalTo(response.getTotalDay())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.theme", Matchers.equalTo(response.getTheme())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.budget", Matchers.equalTo(response.getBudget())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hashtag", Matchers.equalTo(response.getHashtag())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.recommendTarget", Matchers.equalTo(response.getRecommendTarget())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.travelMobility", Matchers.equalTo(response.getTravelMobility())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.restaurantCount", Matchers.equalTo(response.getRestaurantCount())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.spotCount", Matchers.equalTo(response.getSpotCount())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.thumbnail", Matchers.equalTo(response.getThumbnail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.travelPartner", Matchers.equalTo(response.getTravelPartner())));
    }
}