package com.deploy.bemyplan.plan;

import com.deploy.bemyplan.domain.plan.TravelMobility;
import com.deploy.bemyplan.domain.plan.TravelPartner;
import com.deploy.bemyplan.domain.plan.TravelTheme;

import java.util.List;

class PlanFixtures {

    static CreatePlanRequest.CreatePlanRequestBuilder aPlanRequest() {
        return CreatePlanRequest.testBuilder()
                .creator(aCreator().build())
                .plan(aPlan().build())
                .spots(List.of(
                        aSpot().id(2).hasNext(false).build(),
                        aSpot().id(1).hasNext(true)
                                .nextSpot(aNextSpot()
                                        .id(2)
                                        .spentTime(10)
                                        .vehicle(TravelMobility.CAR)
                                        .build())
                                .build()))
                .previews(List.of(
                        aPreview().build()
                ));
    }

    static PreviewDto.PreviewDtoBuilder aPreview() {
        return PreviewDto.testBuilder()
                .image("https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%8B%AC%ED%91%BC/%E1%84%83%E1%85%A9%E1%86%AF%E1%84%86%E1%85%AE%E1%86%AB%E1%84%92%E1%85%AA/%E1%84%83%E1%85%A9%E1%86%AF%E1%84%86%E1%85%AE%E1%86%AB%E1%84%92%E1%85%AA%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB1.jpg")
                .description("제주의 돌의 역사와 신화 등에 대한 것들을 알 수 있고 숲길 코스가 있어 자연 또한 즐길 수 있어요. 실/내외에 여러테마로 이루어져 있어 구경거리가 굉장히 많아요. ");
    }

    static SpotDto.SpotDtoBuilder aSpot() {
        return SpotDto.testBuilder()
                .id(1)
                .address("")
                .date(1)
                .hasNext(true)
                .latitude(33.52139345489709)
                .longitude(126.545603104793)
                .name("번널오름")
                .nextSpot(aNextSpot().build())
                .review("사라봉을 등지고 바다를 향해있는 하얗고 이쁜 등대가 있어요!\n" +
                        "주변에 주차할 자리에 있어서 차에서 잠시 내려서 보면, 푸른 제주 바다와 제주항, 그리고 비행기 풍경을 볼 수 있습니다.\n" +
                        "사람들이 많이 없고 한적한 공간이라 잠시 풍경을 바라보며 사색에 잠길 수 있어요.")
                .savedImages(List.of("image1"))
                .tip("테이크아웃 커피를 가져와 풍경을 보며 마시면 맛이 배가 됩니다!\n" +
                        "작은 낚시 의자, 조립식 캠핑의자가 있다면 미니 캠크닉도 가능해요.")
                ;
    }

    static SpotDto.NextSpot.NextSpotBuilder aNextSpot() {
        return SpotDto.NextSpot.testBuilder()
                .id(1)
                .spentTime(10)
                .vehicle(TravelMobility.CAR);
    }

    static PlanDto.PlanDtoBuilder aPlan() {
        return PlanDto.testBuilder()
                .title("동쪽에서 마주한 제주 향기")
                .description("안녕하세요 :)\n" +
                        "제주의 다양한 모습을 사진으로 담고 있는 픽손이라고 합니다.꾸준하게 다양한 스팟과 관광지의 색다른 모습을 담아내고 있는데요! \n" +
                        "시시각각 변하는 제주의 다양한 모습들을 여행 오실 시기에 맞는 사진으로 보여드리며 여행 계획에 도움 드릴게요!\n" +
                        "돌, 바람, 여자를 제외하고도 바다와 숲 그리고 오름이 유명한 제주는 사시사철 늘 다양한 모습들을 보여주는데요!\n" +
                        "직접 사진을 찍으며 돌아다닌 곳들 중 제주 내음 가득했던 장소들을 몇 곳 선별해보았습니다.\n" +
                        "이번에는 베이직하게 온전히 제주를 느낄 수 있는 장소와 꼭 가봐야 할 추천 관광지를 잘 조화시켜 보여드리고자 합니다.")
                .thumbnail("thumbnail")
                .price(7700)
                .recommend(true)
                .vehicle(TravelMobility.CAR)
                .concept(TravelTheme.HEALING)
                .partner(TravelPartner.COUPLE)
                .cost(250000)
                .period(5);
    }

    static CreatorDto.CreatorDtoBuilder aCreator() {
        return CreatorDto.testBuilder()
                .name("test admin");
    }

}
