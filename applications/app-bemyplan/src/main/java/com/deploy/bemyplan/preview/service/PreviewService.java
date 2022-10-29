package com.deploy.bemyplan.preview.service;

import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.preview.service.dto.PreviewContentListResponse;
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
