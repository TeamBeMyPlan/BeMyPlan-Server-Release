package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.common.Money;
import com.deploy.bemyplan.domain.plan.Plan;
import com.deploy.bemyplan.domain.plan.PlanRepository;
import com.deploy.bemyplan.domain.plan.PlanStatus;
import com.deploy.bemyplan.domain.plan.RcmndStatus;
import com.deploy.bemyplan.domain.plan.RegionType;
import com.deploy.bemyplan.domain.plan.TagInfo;
import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.deploy.bemyplan.plan.PlanFixtures.aPlanRequest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePlanServiceTest {

    private CreatePlanService sut;
    private CreatePreviewService spyPreviewService;
    private PlanRepository spyPlanRepository;

    @BeforeEach
    void setUp() {
        spyPreviewService = mock(CreatePreviewService.class);
        spyPlanRepository = mock(PlanRepository.class);


        sut = new CreatePlanService(spyPlanRepository, new PlanMapper());
    }

    @Test
    void createPlan_passesPlanToRepository() {
        final CreatePlanRequest givenRequest = aPlanRequest()
                .build();

        sut.createPlan(givenRequest);

        Plan expected = Plan.newInstance(2L,
                RegionType.JEJU,
                "thumbnail",
                "동쪽에서 마주한 제주 향기",
                "안녕하세요 :)\n" +
                        "제주의 다양한 모습을 사진으로 담고 있는 픽손이라고 합니다.꾸준하게 다양한 스팟과 관광지의 색다른 모습을 담아내고 있는데요! \n" +
                        "시시각각 변하는 제주의 다양한 모습들을 여행 오실 시기에 맞는 사진으로 보여드리며 여행 계획에 도움 드릴게요!\n" +
                        "돌, 바람, 여자를 제외하고도 바다와 숲 그리고 오름이 유명한 제주는 사시사철 늘 다양한 모습들을 보여주는데요!\n" +
                        "직접 사진을 찍으며 돌아다닌 곳들 중 제주 내음 가득했던 장소들을 몇 곳 선별해보았습니다.\n" +
                        "이번에는 베이직하게 온전히 제주를 느낄 수 있는 장소와 꼭 가봐야 할 추천 관광지를 잘 조화시켜 보여드리고자 합니다.",
                new TagInfo(TravelTheme.HEALING, TravelPartner.COUPLE, TravelMobility.CAR, Money.wons(250000)),
                7700, PlanStatus.ACTIVE, RcmndStatus.RECOMMENDED, Collections.emptyList(), Collections.emptyList());

        verify(spyPlanRepository).save(expected);
    }
}