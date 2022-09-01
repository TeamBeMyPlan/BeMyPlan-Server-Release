package com.deploy.bemyplan.service.preview;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewContentStatus;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.RegionType;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.service.preview.dto.PreviewContentListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PreviewServiceTest {
    private PreviewService previewService;
    private PreviewRepository spyPreviewRepository;

    @BeforeEach
    void setUp() {
        spyPreviewRepository = mock(PreviewRepository.class);
        previewService = new PreviewService(spyPreviewRepository);

    }

    @Test
    void getPreviewContent_callPreviewFromRepository() {
        previewService.getPreviewContent(1L);

        verify(spyPreviewRepository, times(1)).findAllPreviewByPlanId(1L);
    }

    @Test
    void getPreviewContent_returnPreviewContent() {
        //given
        final Plan givenPlan = Plan.newInstance(1L, RegionType.JEJU, "", "", "", TagInfo.testBuilder().build(), 0, PlanStatus.ACTIVE, RcmndStatus.NONE, Collections.emptyList(), Collections.emptyList());
        final Preview givenPreview1 = Preview.newInstance(givenPlan, List.of("image.png", "image2.png"), "description", PreviewContentStatus.ACTIVE, 1L);
        final Preview givenPreview2 = Preview.newInstance(givenPlan, List.of("image3.png", "image4.png"), "description2", PreviewContentStatus.ACTIVE, 2L);
        given(spyPreviewRepository.findAllPreviewByPlanId(givenPlan.getId())).willReturn(List.of(givenPreview1, givenPreview2));
        //when
        PreviewContentListResponse result = previewService.getPreviewContent(givenPlan.getId());

        //then
        assertThat(result.getPreviewContents().size()).isEqualTo(2);
        assertThat(result.getPreviewContents().get(0).getImages()).isEqualTo(List.of("image.png", "image2.png"));
        assertThat(result.getPreviewContents().get(0).getDescription()).isEqualTo("description");
        assertThat(result.getPreviewContents().get(1).getImages()).isEqualTo(List.of("image3.png", "image4.png"));
        assertThat(result.getPreviewContents().get(1).getDescription()).isEqualTo("description2");
    }
}