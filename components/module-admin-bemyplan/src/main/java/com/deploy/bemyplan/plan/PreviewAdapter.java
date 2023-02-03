package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.JsonValueType;
import com.deploy.bemyplan.domain.plan.LegacyPreviewRepository;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewContent;
import com.deploy.bemyplan.domain.plan.PreviewContentStatus;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.image.s3.S3Locator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PreviewAdapter {
    private final LegacyPreviewRepository legacyPreviewRepository;
    private final PreviewRepository previewRepository;

    public void saveAll(final List<PreviewDto> previewDtos, final Plan plan, final List<Spot> spots) {
        final List<Preview> previews = previewDtos.stream()
                .map(preview -> Preview.newInstance(plan, List.of(S3Locator.get(preview.getImage())),
                        preview.getDescription(),
                        PreviewContentStatus.ACTIVE,
                        spots.get(preview.getSpotId()).getId()))
                .collect(Collectors.toList());
        savePreviews(previews);

        final List<PreviewContent> legacyPreviews = new ArrayList<>();
        previewDtos.forEach(preview -> {
            legacyPreviews.add(new PreviewContent(plan, JsonValueType.IMAGE, S3Locator.get(preview.getImage())));
            legacyPreviews.add(new PreviewContent(plan, JsonValueType.TEXT, preview.getDescription()));
        });
        saveLegacyPreviews(legacyPreviews);
    }

    private void savePreviews(final List<Preview> previews) {
        previewRepository.saveAll(previews);
    }

    @Deprecated
    private void saveLegacyPreviews(final List<PreviewContent> legacyPreviews) {
        legacyPreviewRepository.saveAll(legacyPreviews);
    }

    public void deleteByPlanId(final Long planId) {
        legacyPreviewRepository.deleteByPlanId(planId);
        previewRepository.deleteByPlanId(planId);
    }
}
