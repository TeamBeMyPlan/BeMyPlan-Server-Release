package com.deploy.bemyplan.service.preview;

import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.service.preview.dto.PreviewContentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreviewService {
    private final PreviewRepository previewRepository;

    public PreviewContentListResponse getPreviewContent(Long planId) {
        List<Preview> previews = previewRepository.findAllPreviewByPlanId(planId);
        return PreviewContentListResponse.of(previews);
    }
}
