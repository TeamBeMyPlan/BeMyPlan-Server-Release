package com.deploy.bemyplan.creator.controller;

import com.deploy.bemyplan.creator.service.CreatorService;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.plan.service.dto.response.CreatorPlanResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static com.deploy.bemyplan.domain.plan.Plan.newInstance;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CreatorControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CreatorService stubCreatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new CreatorController(stubCreatorService))
                .build();
    }

    @Test
    void getCreatorPlansReturnsOKStatus() throws Exception {
        mockMvc.perform(get("/api/v1/creators/plans"))
                .andExpect(status().isOk());
    }

    @Test
    void getCreatorPlansReturnsResponse() throws Exception {
        Plan givenPlan = newInstance(1L, RegionCategory.JEJU, Region.JEJUALL, "thumbnail", "title", "description", TagInfo.testBuilder().build(), 2000, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        CreatorPlanResponse response = CreatorPlanResponse.of(givenPlan);
        BDDMockito.given(stubCreatorService.getCreatorPlans(ArgumentMatchers.any())).willReturn(List.of(response));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/creators/plans", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].planId", Matchers.equalTo(response.getPlanId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.equalTo(response.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].region", Matchers.equalTo(response.getRegion().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].thumbnailUrl", Matchers.equalTo(response.getThumbnailUrl())));
    }
}