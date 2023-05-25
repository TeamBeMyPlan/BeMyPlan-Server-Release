package com.deploy.bemyplan.plan.controller;

import com.deploy.bemyplan.domain.plan.TravelTheme;
import com.deploy.bemyplan.plan.service.GetPlansByThemeRequest;
import com.deploy.bemyplan.plan.service.PlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlanControllerTest {

    private MockMvc mockMvc;
    private PlanService spyPlanService;

    @BeforeEach
    void setUp() {
        spyPlanService = mock(PlanService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PlanController(spyPlanService))
                .build();
    }

    @Test
    @DisplayName("테마별 일정보기")
    void getPlansByTheme() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/plans/theme")
                                .param("includes", "HEALING,EATING,HOTPLACE"))
                .andExpect(status().isOk());

        GetPlansByThemeRequest request = new GetPlansByThemeRequest(List.of(TravelTheme.HEALING, TravelTheme.EATING, TravelTheme.HOTPLACE), List.of());
        verify(spyPlanService).getPlansByTheme(request);
    }
}