package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.LegacyPreviewRepository;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PreviewAdapter {
    private final LegacyPreviewRepository legacyPreviewRepository;
    private final PreviewRepository previewRepository;

    public void saveAll(List<Preview> previews) {
        previewRepository.saveAll(previews);
    }

    @Deprecated
    public void saveLegacyAll(List<PreviewContent> legacyPreviews) {
        legacyPreviewRepository.saveAll(legacyPreviews);
    }
}
