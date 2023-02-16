package com.deploy.bemyplan.preview.controller;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewContentStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.Region;
import com.deploy.bemyplan.domain.plan.RegionCategory;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotImage;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.preview.service.PreviewService;
import com.deploy.bemyplan.preview.service.dto.PreviewContentListResponse;
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
        final Plan givenPlan = Plan.newInstance(1L, RegionCategory.JEJU, Region.JEJUALL, "", "", "", TagInfo.testBuilder().build(), 0, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        Spot spot1 = new Spot(null, null, null, null, null, null, 0, null, null);
        spot1.setImage(List.of(new SpotImage("image.png", spot1), new SpotImage("image2.png", spot1)));
        final Preview givenPreview1 = Preview.newInstance(givenPlan,"description", PreviewContentStatus.ACTIVE, spot1);
        Spot spot2 = new Spot(null, null, null, null, null, null, 0, null, null);
        spot2.setImage(List.of(new SpotImage("image3.png", spot2), new SpotImage("image4.png", spot2)));
        final Preview givenPreview2 = Preview.newInstance(givenPlan,"description2", PreviewContentStatus.ACTIVE, spot2);
        given(stubPreviewService.getPreviewContent(any())).willReturn(PreviewContentListResponse.of(List.of(givenPreview1, givenPreview2)));

        mockMvc.perform(get("/api/v2/preview/{planId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.previewContents[0].images", equalTo(List.of("image.png", "image2.png"))))
                .andExpect(jsonPath("$.previewContents[0].description", equalTo("description")))
                .andExpect(jsonPath("$.previewContents[1].images", equalTo(List.of("image3.png", "image4.png"))))
                .andExpect(jsonPath("$.previewContents[1].description", equalTo("description2")));
    }
}