package com.deploy.bemyplan.plan.application;

import com.deploy.bemyplan.domain.plan.Location;
import com.deploy.bemyplan.domain.plan.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.Spot;
import com.deploy.bemyplan.domain.plan.SpotImage;
import com.deploy.bemyplan.domain.plan.SpotRepository;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.image.s3.S3Locator;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanRequest;
import com.deploy.bemyplan.plan.application.port.in.UpdatePlanUseCase;
import com.deploy.bemyplan.plan.application.port.in.UpdateSpotRequest;
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

    @Override
    public void updatePlan(UpdatePlanRequest request) {
        Plan plan = planRepository.findById(request.getPlanId())
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
    public void updateSpots(List<UpdateSpotRequest> requests) {
        requests.stream()
                .forEach(spotDto -> {
                    Spot spot = spotRepository.findById(spotDto.getId())
                            .orElseThrow(IllegalArgumentException::new);

                    spot.setTitle(spotDto.getName());
                    spot.setCategory(spotDto.getType());
                    spot.setLocation(Location.of(spotDto.getAddress(), spotDto.getLongitude(), spotDto.getLatitude()));
                    spot.setTip(spotDto.getTip());
                    spot.setReview(spotDto.getReview());
                    spot.setDay(spotDto.getDate());
                    spot.setImage(spotDto.getSavedImages().stream()
                            .map(image -> new SpotImage(S3Locator.get(image), spot))
                            .collect(Collectors.toList()));
                });
    }
}
