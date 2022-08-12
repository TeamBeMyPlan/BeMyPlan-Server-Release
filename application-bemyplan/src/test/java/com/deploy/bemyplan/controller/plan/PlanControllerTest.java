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
        mockMvc.perform(get("/api/v1/plan/preview/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPlanPreviewPassesPlanIdToService() throws Exception {
        mockMvc.perform(get("/api/v1/plan/preview/1"));

        verify(stubPlanService, times(1)).getPlanPreview(1L);
    }

    @Test
    void getPlanPreviewReturnsResponse() throws Exception {
        Plan givenPlan = newInstance(1L, RegionType.JEJU, "thumbnail", "title", "description", TagInfo.testBuilder().build(), 2000, PlanStatus.ACTIVE, RcmndStatus.NONE);
        PlanPreviewResponseDto response = PlanPreviewResponseDto.of(givenPlan, List.of("image.png", "image2.png"), 21, 1);
        given(stubPlanService.getPlanPreview(any())).willReturn(response);

        mockMvc.perform(get("/api/v1/plan/preview/{planId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planId", equalTo(response.getPlanId())))
                .andExpect(jsonPath("$.title", equalTo(response.getTitle())))
                .andExpect(jsonPath("$.description", equalTo(response.getDescription())))
                .andExpect(jsonPath("$.price", equalTo(response.getPrice())))
                .andExpect(jsonPath("$.month", equalTo(response.getMonth())))
                .andExpect(jsonPath("$.totalDay", equalTo(response.getTotalDay())))
                .andExpect(jsonPath("$.theme", equalTo(response.getTheme())))
                .andExpect(jsonPath("$.budget", equalTo(response.getBudget())))
                .andExpect(jsonPath("$.hashtag", equalTo(response.getHashtag())))
                .andExpect(jsonPath("$.recommendTarget", equalTo(response.getRecommendTarget())))
                .andExpect(jsonPath("$.travelMobility", equalTo(response.getTravelMobility())))
                .andExpect(jsonPath("$.restaurantCount", equalTo(response.getRestaurantCount())))
                .andExpect(jsonPath("$.spotCount", equalTo(response.getSpotCount())))
                .andExpect(jsonPath("$.thumbnail", equalTo(response.getThumbnail())))
                .andExpect(jsonPath("$.budget", equalTo(response.getTravelPartner())));
    }
}