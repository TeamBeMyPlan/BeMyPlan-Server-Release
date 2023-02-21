package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.Location;
import com.deploy.bemyplan.domain.plan.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.Preview;
import com.deploy.bemyplan.domain.plan.PreviewRepository;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.image.s3.S3Locator;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanRequest;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.UpdatePreviewRequests;
import com.deploy.bemyplan.plan.application.port.in.UpdateSpotRequests;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
class UpdatePlanService implements UpdatePlanUseCase {
    private final PlanRepository planRepository;
    private final SpotRepository spotRepository;
    private final PreviewRepository previewRepository;

    @Override
    public void updatePlan(final UpdatePlanRequest request) {
        final Plan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(IllegalArgumentException::new);

        plan.setCreatorId(request.getCreatorId());
        plan.setTitle(request.getTitle());
        plan.setDescription(request.getDescription());
        plan.setThumbnailUrl(S3Locator.get(request.getThumbnail()));
        plan.setPrice(request.getPrice());
        plan.setRcmndStatus(RcmndStatus.of(request.isRecommend()));
        plan.setTagInfo(new TagInfo(request.getConcept(),
                request.getPartner(),
                request.getVehicle(),
                Money.wons(request.getCost()),
                request.getPeriod(),
                0));
        plan.setRegion(request.getRegion());
        plan.setHashtags(Arrays.asList(request.getTags().split(",")));
        plan.setRecommendTargets(Arrays.asList(request.getRecommendTargets().split(",")));
    }

    @Override
    public void updateSpots(final UpdateSpotRequests requests) {
        final long planId = requests.getPlanId();
        final Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다.: " + planId));

        final List<Spot> spots = requests.getItems().stream()
                .map(item -> new Spot(item.getId(),
                        item.getName(),
                        item.getType(),
                        Location.of(item.getAddress(), item.getLatitude(), item.getLongitude()),
                        item.getTip(),
                        item.getReview(),
                        item.getSavedImages().stream().map(image -> S3Locator.get(image)).collect(Collectors.toList()),
                        plan,
                        item.getDate(),
                        item.getVehicle(),
                        item.getSpentTime()
                )).collect(Collectors.toList());
        spotRepository.saveAll(spots);
    }

    @Override
    public void updatePreviews(final UpdatePreviewRequests requests) {
        final long planId = requests.getPlanId();
        final Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다.: " + planId));

        List<Preview> previews = requests.getItems().stream()
                .map(item -> {
                    final Spot spot = spotRepository.findById(item.getSpotId())
                            .orElseThrow(IllegalArgumentException::new);
                    return new Preview(item.getId(), plan, item.getDescription(), spot);
                }).collect(Collectors.toList());
        previewRepository.saveAll(previews);
    }
}
