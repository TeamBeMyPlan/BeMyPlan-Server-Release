package com.deploy.bemyplan.controller.plan;

import com.deploy.bemyplan.service.plan.PlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}