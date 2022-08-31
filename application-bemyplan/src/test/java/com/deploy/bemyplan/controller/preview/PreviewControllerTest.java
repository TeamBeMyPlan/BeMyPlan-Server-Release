package com.deploy.bemyplan.controller.preview;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewContentStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.RegionType;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.service.preview.PreviewService;
import com.deploy.bemyplan.service.preview.dto.PreviewContentListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PreviewControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PreviewService stubPreviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new PreviewController(stubPreviewService)).build();
    }

    @Test
    void getPreviewContent_ReturnsOkHttpsStatus() throws Exception {
        mockMvc.perform(get("/api/v2/preview/{planId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getPreviewContent_PassesPlanIdToService() throws Exception {
        mockMvc.perform(get("/api/v2/preview/{planId}", 1))
                .andExpect(status().isOk());

        verify(stubPreviewService, times(1)).getPreviewContent(1L);
    }

    @Test
    void getPreviewContent_ReturnsPreviewContentsDto() throws Exception {
        //given
        final Plan givenPlan = Plan.newInstance(1L, RegionType.JEJU, "", "", "", TagInfo.testBuilder().build(), 0, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        final Preview givenPreview1 = Preview.newInstance(givenPlan, List.of("image.png", "image2.png"), "description", PreviewContentStatus.ACTIVE, 1L);
        final Preview givenPreview2 = Preview.newInstance(givenPlan, List.of("image3.png", "image4.png"), "description2", PreviewContentStatus.ACTIVE, 2L);
        given(stubPreviewService.getPreviewContent(any())).willReturn(PreviewContentListResponse.of(List.of(givenPreview1, givenPreview2)));

        mockMvc.perform(get("/api/v2/preview/{planId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.previewContents[0].images", equalTo(List.of("image.png", "image2.png"))))
                .andExpect(jsonPath("$.data.previewContents[0].description", equalTo("description")))
                .andExpect(jsonPath("$.data.previewContents[1].images", equalTo(List.of("image3.png", "image4.png"))))
                .andExpect(jsonPath("$.data.previewContents[1].description", equalTo("description2")));
    }

}