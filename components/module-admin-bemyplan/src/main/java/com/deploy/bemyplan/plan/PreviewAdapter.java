package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewContentStatus;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.image.s3.S3Locator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PreviewAdapter {
    private final PreviewRepository previewRepository;

    public void saveAll(final List<PreviewDto> previewDtos, final Plan plan, final List<Spot> spots) {
        final List<Preview> previews = previewDtos.stream()
                .map(preview -> Preview.newInstance(plan, List.of(S3Locator.get(preview.getImage())),
                        preview.getDescription(),
                        PreviewContentStatus.ACTIVE,
                        spots.get(preview.getSpotSeq()).getId()))
                .collect(Collectors.toList());
        savePreviews(previews);
    }

    private void savePreviews(final List<Preview> previews) {
        previewRepository.saveAll(previews);
    }


    public void deleteByPlanId(final Long planId) {
        previewRepository.deleteByPlanId(planId);
    }
}
