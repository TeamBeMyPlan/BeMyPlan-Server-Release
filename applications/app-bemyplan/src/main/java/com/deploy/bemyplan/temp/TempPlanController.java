package com.deploy.bemyplan.temp;


import com.deploy.bemyplan.temp.response.TempCreatorInfoResponse;
import com.deploy.bemyplan.temp.response.TempPlanResponse;
import com.deploy.bemyplan.temp.response.TempScheduleDetailResponse;
import com.deploy.bemyplan.temp.response.TempSpotDetailResponse;
import com.deploy.bemyplan.temp.response.TempSpotImageResponse;
import com.deploy.bemyplan.temp.response.TempSpotMoveInfoDetailResponse;
import com.deploy.bemyplan.temp.response.TempSpotMoveInfoResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.deploy.bemyplan.domain.plan.TravelMobility.CAR;
import static com.deploy.bemyplan.domain.plan.TravelMobility.WALK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TempPlanController {

    @ApiOperation("구매 여행 일정 예시를 가져옵니다.")
    @GetMapping("/v2/temp/plan")
    public TempPlanResponse getTempPlan() {
        return TempPlanResponse.of(
                -1L,
                "자연스러운 제주",
                getTempUserInfo(),
                List.of(
                        getFirstDayPlan(),
                        getSecondDayPlan(),
                        getThirdDayPlan()
                )
        );
    }

    @ApiOperation("구매 여행 일정 예시를 가져옵니다.")
    @GetMapping("/v2/temp/plan/moveInfo")
    public List<TempSpotMoveInfoResponse> getTempPlanMoveInfo() {
        return List.of(
                getFirstDayPlanMoveInfo(),
                getSecondDayPlanMoveInfo(),
                getThirdDayPlanMoveInfo()
        );
    }

    @NotNull
    private TempCreatorInfoResponse getTempUserInfo() {
        return TempCreatorInfoResponse.of(-1L, "린지");
    }

    @NotNull
    private TempSpotMoveInfoResponse getThirdDayPlanMoveInfo() {
        return TempSpotMoveInfoResponse.of(
                3,
                List.of(
                        TempSpotMoveInfoDetailResponse.of(1L, 2L, CAR, 31)
                )
        );
    }

    @NotNull
    private TempSpotMoveInfoResponse getSecondDayPlanMoveInfo() {
        return TempSpotMoveInfoResponse.of(
                2,
                List.of(
                        TempSpotMoveInfoDetailResponse.of(1L, 2L, CAR, 2),
                        TempSpotMoveInfoDetailResponse.of(2L, 3L, CAR, 39)
                )
        );
    }

    @NotNull
    private TempSpotMoveInfoResponse getFirstDayPlanMoveInfo() {
        return TempSpotMoveInfoResponse.of(
                1,
                List.of(
                        TempSpotMoveInfoDetailResponse.of(1L, 2L, CAR, 41),
                        TempSpotMoveInfoDetailResponse.of(2L, 3L, WALK, 1),
                        TempSpotMoveInfoDetailResponse.of(3L, 4L, CAR, 53)
                )
        );
    }

    @NotNull
    private TempScheduleDetailResponse getThirdDayPlan() {
        return TempScheduleDetailResponse.of(
                List.of(
                        TempSpotDetailResponse.of("스타벅스 제주도남DT점", "제주특별자치도 제주시 애월읍 애월해안로 376", 33.4727008501526,
                                126.348753870498,
                                List.of("1. 리유저블 컵을 반납하기 어려운신 분들은 제주공항에 그 컵을 들고 가시면 수속밟기 전에 식수대 있는 곳을 잘 보시면 해피해빗이라고 리유저블 컵 반납기를 찾을 수 있어요. 음료를 결제할 때 부과했던 1000원은 현금, 스타벅스 카드 (충전금), 혹은 해피해빗 포인트로 환불 받을 수 있어요.")
                                ,
                                "제주도 가면 꼭 스타벅스 일부로라도 들려야 되는 거 아시죠? 그 이유는 제주도 스타벅스는 제주도에서만 파는 한정 메뉴들이 있기 때문이에요. 저희는 드라이브 하면서 음료를 마시고 싶어서 드라이브스루를 이용했어요. 제주도 스타벅스 전매장에서는 에코매장으로 일회용컵이 없고 개인컵으로 혹은 리유저블 컵을 1000원 주고 주문해야 했어요. 이렇게 리유저블 컵으로만 주문을 받는 곳은 제주도가 처음이라 처음엔 당황스러웠지만 환경을 생각한다는 취지에서는 정말 좋은 발걸음이라고 생각했어요. 각 음료마다 주문시에 1000원이 부과되만, 리유저블 컵을 다시 반납하면 부과한 1000원을 다시 돌려받을 수 있는 시스템이었어요."
                                ,
                                List.of(
                                        TempSpotImageResponse.of(1L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/3-1+%EC%8A%A4%ED%83%80%EB%B2%85%EC%8A%A4+%EB%93%9C%EB%9D%BC%EC%9D%B4%EB%B8%8C%EC%8A%A4%EB%A3%A8/%E1%84%89%E1%85%B3%E1%84%90%E1%85%A1%E1%84%87%E1%85%A5%E1%86%A8%E1%84%89%E1%85%B3+%E1%84%83%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%E1%85%B5%E1%84%87%E1%85%B3%E1%84%89%E1%85%B3%E1%84%85%E1%85%AE.jpeg")
                                )
                        ),
                        TempSpotDetailResponse.of("제주승마공원", "제주특별자치도 제주시 애월읍 녹고메길 152-1", 33.405112650286, 126.406064378018,
                                List.of("1. 제주승마공원은 차로 이동하는게 편할 것 같아요. 지리적으로 승마공원은 높은 언덕 위에 위치해 있기 때문이에요. 공원 입구쪽에 주차공간은 넉넉했어요.\n",
                                        "2. 사진을 위해서는 전체적인 스타일링도 중요해요~! 꼭 승마공원에 있는 헬멧, 조끼, 부츠까지 아웃핏 풀장착하기! 저희는 헬멧부터 색 열심히 골랐는데 검정 헬멧보다는 갈색 벨벳추천 드리고, 부츠까지 신어야 프로페셔널한 느낌 장착 가능해요. 그리고 스냅사진 포즈 미리 정하는 것도 잊지마세요! ")
                                ,
                                "제주도에 가면 꼭 하나라도 액티비티 해야 하는 린지! 여기 제주승마공원에서는 고급승마 조끼, 헬멧, 부츠까지 무료로 대여할 수 있어요. 숲길과 초원 둘 중에 하나 고를 수 있고 둘 다 A는 15분/ B는 30분 가격에 따라 선택 가능했답니다. 초원을 하고 싶었지만 저희가 갔을 때에는 공사 중이여서 숲길 30분으로 선택했답니다. 제주도에서 승마체험을 찾아보면 다른 곳도 정보가 많은데 저희가 굳이 제주승마공원을 고집한 이유는 바로 액자사진 촬영과 스냅사진 10컷을 저희가 승마하는 중에 찍어 주는 코스가 있었기 때문이에요. 승마를 하면서 한 손으로 셀카를 찍거나 사진을 서로 찍는 건 무리가 있을 것 같았기 때문에 촬영 코스를 선택했어요 ㅎㅎ. 액자 사진은 승마하고 나서 10분정도 뒤에 바로 주시고, 스냅사진 10장은 3-4일 뒤에 사진작가님이 보정 후 이메일로 보내주신답니다. 인화해주신 사진을 확인해 보니 정말 멋있게 나왔더라고요! 저희처럼 승마도 체험하고 멋있는 사진도 남기고 싶으시다면 제주승마공원 추천드릴게요! 게다가 말 타고 승마공원 주위를 보면 정말로 멋있는 풍경을 볼 수 있어요. 정말 여기는 강추입니다! "
                                ,
                                List.of(
                                        TempSpotImageResponse.of(1L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/3-2+%EC%8A%B9%EB%A7%88%EA%B3%B5%EC%9B%90/%E1%84%8C%E1%85%A6%E1%84%8C%E1%85%AE%E1%84%89%E1%85%B3%E1%86%BC%E1%84%86%E1%85%A1%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+1.jpeg"),
                                        TempSpotImageResponse.of(2L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/3-2+%EC%8A%B9%EB%A7%88%EA%B3%B5%EC%9B%90/%E1%84%89%E1%85%B3%E1%86%BC%E1%84%86%E1%85%A1%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+2.jpeg"),
                                        TempSpotImageResponse.of(3L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/3-2+%EC%8A%B9%EB%A7%88%EA%B3%B5%EC%9B%90/%E1%84%8C%E1%85%A6%E1%84%8C%E1%85%AE%E1%84%89%E1%85%B3%E1%86%BC%E1%84%86%E1%85%A1%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+3.jpeg"),
                                        TempSpotImageResponse.of(4L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/3-2+%EC%8A%B9%EB%A7%88%EA%B3%B5%EC%9B%90/%E1%84%89%E1%85%B3%E1%86%BC%E1%84%86%E1%85%A1%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+4.jpeg"),
                                        TempSpotImageResponse.of(5L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/3-2+%EC%8A%B9%EB%A7%88%EA%B3%B5%EC%9B%90/%E1%84%89%E1%85%B3%E1%86%BC%E1%84%86%E1%85%A1%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+5.jpeg"),
                                        TempSpotImageResponse.of(6L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/3-2+%EC%8A%B9%EB%A7%88%EA%B3%B5%EC%9B%90/%E1%84%89%E1%85%B3%E1%86%BC%E1%84%86%E1%85%A1%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+6.jpeg"),
                                        TempSpotImageResponse.of(7L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/3-2+%EC%8A%B9%EB%A7%88%EA%B3%B5%EC%9B%90/%E1%84%89%E1%85%B3%E1%86%BC%E1%84%86%E1%85%A1%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+7.jpeg"),
                                        TempSpotImageResponse.of(8L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/3-2+%EC%8A%B9%EB%A7%88%EA%B3%B5%EC%9B%90/%E1%84%89%E1%85%B3%E1%86%BC%E1%84%86%E1%85%A1%E1%84%80%E1%85%A9%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB+8.jpeg")
                                )
                        )
                )
        );
    }

    @NotNull
    private TempScheduleDetailResponse getSecondDayPlan() {
        return TempScheduleDetailResponse.of(
                List.of(
                        TempSpotDetailResponse.of("대우정", "제주특별자치도 제주시 서사로 152", 33.4989134428429, 126.519577334316,
                                List.of("1. 사진을 찍기에는 분위기가 너무 식당스러워서 음식만 찍어야 해요. 사실 보이는 것보다 사진이 좀 잘 나와서 당황스러운 곳이에요.",
                                        "2. 주차공간 따로 마련 안돼있고 알아서 뒷길에 골목주차해야해요.")
                                ,
                                "제주도에 왔는데 전복은 먹어야겠다고 생각해서 맛집들을 잘 아는 맛집 인플루언서들이 한 번씩은 가 본 대우정을 방문했어요. 저희는 전복 돌솥밥이랑 성게 미역국을 시켰어요. 식당 분위기는 그냥 일반 식당 분위기여서 사진으로는 정말 맛있어 보여서 기대했는데 생각보다 맛은 너무 평범했어요. 전복이 먹고 싶어서 왔는데 전복 돌솥밥에는 사진처럼 많지 않고 되게 얇고 작아서 비비면 없어진답니다. 성게 미역국도 간이 조금 쎄고 성게가 안보였어요. 역시 맛집이라고 하는 곳은 의심의 끈을 놓치면 안되는 것 같아요. 다만, 공항이랑 가까워서 배가 너무 고픈데, 그래도 제주도에만 있는 식당 가야겠다면 한 번만 가세요.."
                                ,
                                List.of(
                                        TempSpotImageResponse.of(1L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/2-2+%EB%8C%80%EC%9A%B0%EC%A0%95/%E1%84%83%E1%85%A2%E1%84%8B%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%BC+1.jpeg"),
                                        TempSpotImageResponse.of(2L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/2-2+%EB%8C%80%EC%9A%B0%EC%A0%95/%E1%84%83%E1%85%A2%E1%84%8B%E1%85%AE%E1%84%8C%E1%85%A5%E1%86%BC+2.jpeg")
                                )
                        ),
                        TempSpotDetailResponse.of("돌카롱", "제주특별자치도 제주시 서광로2길 27-2", 33.4985217309384, 126.513645600081,
                                List.of("1. 주차는 어려워서 일행이 있다면 초스피드 필요! 저희는 밥 먹고 천천히 걸어갔답니다.")
                                ,
                                "대우정에서 걸어서도 충분히 가는 돌카롱이에요. 마카롱 좋아하시는 분들이라면 돌카롱도 좋아하실거라 믿어요. 여기 마카롱은 꼬끄가 엄청 쫀득해요! 평소에 먹던 마카롱과는 사뭇 다르지만 더 맛있는 것 같아요. 제 최애 마카롱이에요ㅎㅎ 겉에서 보는거와 달리 돌카롱 내부는 정말 그냥 딱 마카롱만 구매하고 나오는 느낌이에요. 공항 근처에 위치해 있어서 하나 사가는거 추천드릴게요."
                                ,
                                List.of(
                                        TempSpotImageResponse.of(1L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/2-3+%EB%8F%8C%EC%B9%B4%EB%A1%B1/%E1%84%83%E1%85%A9%E1%86%AF%E1%84%8F%E1%85%A1%E1%84%85%E1%85%A9%E1%86%BC+1.jpeg")
                                )
                        ),
                        TempSpotDetailResponse.of("푸른밤의해안속초식당", "제주특별자치도 제주시 노형로 40", 33.4624621496069, 126.451488498327,
                                List.of("1. 주차공간 엄청 넓습니다. 일행이 많거나 단체일 경우에 방문하면 편할 것 같아요.", "2. 구이류 보다는 조림을 시키는 거 추천드리고, 조림은 인수보다 넉넉하게 시키기!")
                                ,
                                "저희는 여기서 갈치조림 소자 사이즈와 고등어구이를 먹었어요. 뭔가 맛집 분위기보다는 현지인들이 단체모임이나 이런거 있을때 갈 것 같은 곳이였어요. 저희가 갔을때에는 아저씨들이 많았어요. 생각보다 갈치조림이 너무 양이 작고 밑반찬 이런것들이 초라해보여서 실망했지만 갈치조림 국물이 생각보다 맛있어서 놀랐어요. 고등어구이도 나쁘지 않았구요! 속으로 이래서 맛집이라고 한건가? 라는 생각도 했어요.. ㅋㅋㅋ 그렇기에 갈치에 대한 기대가 너무 크지만 않다면 제주시내로 가는길에 추천드릴게요."
                                ,
                                List.of(
                                        TempSpotImageResponse.of(1L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/2-4+%ED%91%B8%EB%A5%B8%EB%B0%A4%EC%9D%98%ED%95%B4%EC%95%88%EC%86%8D%EC%B4%88%EC%8B%9D%EB%8B%B9/%E1%84%91%E1%85%AE%E1%84%85%E1%85%B3%E1%86%AB%E1%84%87%E1%85%A1%E1%86%B7%E1%84%8B%E1%85%B4%E1%84%92%E1%85%A2%E1%84%8B%E1%85%A1%E1%86%AB%E1%84%89%E1%85%A9%E1%86%A8%E1%84%8E%E1%85%A9%E1%84%89%E1%85%B5%E1%86%A8%E1%84%83%E1%85%A1%E1%86%BC+1.jpeg"),
                                        TempSpotImageResponse.of(2L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/2-4+%ED%91%B8%EB%A5%B8%EB%B0%A4%EC%9D%98%ED%95%B4%EC%95%88%EC%86%8D%EC%B4%88%EC%8B%9D%EB%8B%B9/%E1%84%91%E1%85%AE%E1%84%85%E1%85%B3%E1%86%AB%E1%84%87%E1%85%A1%E1%86%B7%E1%84%8B%E1%85%B4%E1%84%92%E1%85%A2%E1%84%8B%E1%85%A1%E1%86%AB%E1%84%89%E1%85%A9%E1%86%A8%E1%84%8E%E1%85%A9%E1%84%89%E1%85%B5%E1%86%A8%E1%84%83%E1%85%A1%E1%86%BC+2.jpeg")
                                )
                        )
                )
        );
    }

    @NotNull
    private TempScheduleDetailResponse getFirstDayPlan() {
        return TempScheduleDetailResponse.of(
                List.of(
                        TempSpotDetailResponse.of("아가스트 요가", "제주특별자치도 서귀포시 대정읍 연명로 228-11", 33.2923113681473, 126.255567603996,
                                List.of("1. 첫째 날 숙박한 항공우주호텔은 아가스트 요가원이랑 10분 거리에 위치했어서 이른 아침 요가도 무리 없이 들을 수 있었답니다~!", "2. 주차공간은 요가원 앞에 차를 댈 수 있는 공간이 있어요.", "3. 맨 뒷 자리나 끝 쪽에서 수련을 한다면 사람들에게 피해주지 않는 선에서 핸드폰을 세워 놓고 영상 촬영하면 좋을 것 같아요. 수업 전에 선생님께 미리 양해 구하기 필수!")
                                ,
                                "힐링이 주된 목적이었기 때문에 평소 요가를 좋아하는 저는 자연을 오롯이 느낄 수 있는 곳에서 요가를 해보고 싶었어요! 그래서 제일 제주도스럽고 자연을 느낄 수 있는 요가원인 아가스트 요가를 방문하기로 했답니다. 요가원은 야외에 설치 되어있는 오두막 안에서 수업을 들을 수 있답니다. 미리 선생님께서 인원수에 맞게 매트와 담요를 준비해 주셨어요. 겨울이라 추울 수도 있겠다고 생각했는데 바람이 들어오지 않는 천막과 안에서 장작으로 불을 떼우는 난로가 있어서 매우 따뜻했어요. 장작 타는 소리와 새가 지저귀는 소리는 정말 힐링 그 자체였답니다. 여기는 사계절 다 가능한 요가원이여서 언제든 방문해서 여행 중에 몸과 마음의 힐링을 찾을 수 있을 것 같아요. 개인적으로 저는 날씨가 조금 더 따뜻해진다면 자연을 더 느끼며 요가 할 수 있을 것 같아서 꼭 다시 한 번 방문하고 싶은 곳이에요."
                                ,
                                List.of(
                                        TempSpotImageResponse.of(1L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-3+%EC%95%84%EA%B0%80%EC%8A%A4%ED%8A%B8+%EC%9A%94%EA%B0%80/%E1%84%8B%E1%85%A1%E1%84%80%E1%85%A1%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3+%E1%84%8B%E1%85%AD%E1%84%80%E1%85%A1+1.jpeg"),
                                        TempSpotImageResponse.of(2L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-3+%EC%95%84%EA%B0%80%EC%8A%A4%ED%8A%B8+%EC%9A%94%EA%B0%80/%E1%84%8B%E1%85%A1%E1%84%80%E1%85%A1%E1%84%89%E1%85%B3%E1%84%90%E1%85%B3+%E1%84%8B%E1%85%AD%E1%84%80%E1%85%A1+2.jpeg")
                                )
                        ),
                        TempSpotDetailResponse.of("리틀넥 제주애월", "제주특별자치도 제주시 애월읍 애월북서길 56-1", 33.4629252295724, 126.309244734174,
                                List.of("1. 주차는 공용주차장을 이용해야하며 5만원 이상은 무료주차이고 그 이하는 2,000원에 가능한데 영수증 꼭꼭 지참해야 한다는 점 잊지마세요!",
                                        "2. 몽상드 애월 밖에는 앉을 수 있는 야외공간이 예쁘게 마련되어 있어요. 꼭 바다가 보이는 배경으로 피쉬앤칩스 시켜서 사진 남겨야 해요! 이게 바로 리틀넥 제주애월에서만 가능하다는거 아시죠?")
                                ,
                                "제주도까지 가서 웬 리틀넥? 이라고 하실 수도 있지만 허기진 배를 간단하게 채우고자 애월 해변가도 볼겸, 겸사겸사 핫플인 애월카페거리에 있는 리틀넥을 방문했어요. 리틀넥은 서울에도 여러 지점이 있지만 서울 리틀넥은 항상 답답하다고 느꼈는데 애월점은 탁 트인 오션뷰라는 점! 굳이 리틀넥까지 온 이유는 바로 이 뷰 때문이에요! 메뉴가 서울 리틀넥과는 또 다르더라고요. 그래서 저희는 베스트 메뉴인 피쉬앤칩스와 시금치스프, 그리고 저희가 좋아하는 하와이안 살몬 보울 (포케)을 먹었어요. 맛은 리틀넥이니깐 확실히 보장해요!"
                                ,
                                List.of(
                                        TempSpotImageResponse.of(1L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-2+%EB%A6%AC%ED%8B%80%EB%84%A5/%E1%84%85%E1%85%B5%E1%84%90%E1%85%B3%E1%86%AF%E1%84%82%E1%85%A6%E1%86%A8+0.jpeg"),
                                        TempSpotImageResponse.of(2L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-2+%EB%A6%AC%ED%8B%80%EB%84%A5/%E1%84%85%E1%85%B5%E1%84%90%E1%85%B3%E1%86%AF%E1%84%82%E1%85%A6%E1%86%A8+1.jpeg"),
                                        TempSpotImageResponse.of(3L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-2+%EB%A6%AC%ED%8B%80%EB%84%A5/%E1%84%85%E1%85%B5%E1%84%90%E1%85%B3%E1%86%AF%E1%84%82%E1%85%A6%E1%86%A8+2.jpeg")
                                )
                        ),
                        TempSpotDetailResponse.of("한담해안산책로", "제주특별자치도 제주시 애월읍 곽지리 1359", 33.4590846763959, 126.310575628004,
                                List.of("1. 바닷가라 바람이 많이 불어서 바람을 최대한 등지고 사진 찍기! 인물을 너무 멀리서 찍기보다는 가까이에서 얼굴 보이게 찍는것이 더 예쁘더라고요.")
                                ,
                                "리틀넥에서 밥먹고 앞에 보이는 바닷가를 따라 길을 걷다 보니 예쁜 산책로가 나왔는데 그게 바로 한담해변가 산책로! 길을 너무 예쁘게 조성해놔서 밥먹고 천천히 바다 보면서 걷기 좋은 것 같아요."
                                ,
                                List.of(
                                        TempSpotImageResponse.of(1L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-4+%ED%95%9C%EB%8B%B4%ED%95%B4%EB%B3%80/%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%86%B7+1.jpeg"),
                                        TempSpotImageResponse.of(2L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-4+%ED%95%9C%EB%8B%B4%ED%95%B4%EB%B3%80/%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%86%B7+2.jpeg"),
                                        TempSpotImageResponse.of(3L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-4+%ED%95%9C%EB%8B%B4%ED%95%B4%EB%B3%80/%E1%84%92%E1%85%A1%E1%86%AB%E1%84%83%E1%85%A1%E1%86%B7+3.jpeg")
                                )
                        ),
                        TempSpotDetailResponse.of("동문재래시장", "제주특별자치도 제주시 관덕로14길 20", 33.5115902822492, 126.526018218964,
                                List.of("1. 제주도 기념품 사가야 한다면 회 포장 기다리면서 재래시장쪽 둘러보는거 추천드려요. 제주공항에서 사는 것보다 훨씬 사게 구매 가능해요.")
                                ,
                                "저희는 동문시장에서 저녁으로 먹을 올레수산에서 회와 딱새우를 포장했어요. 원래 남해수산이 바로 회를 떠주고 회 상태도 신선하니 좋다고 해서 찾아갔는데 아쉽게도 휴무날이여서 아쉬운대로 대기하는 사람이 많은 올레수산에 갔어요. 원하는 한 접시를 예약하고 10분에서 15분 정도 기다리라고 하셔서 기다리는 동안 시장을 한 바퀴 돌고 왔더니 저희께 이미 나와있었어요. 동문시장에서 회를 드신다면 남해수산이랑 올레수산에서 회포장 하시는거 추천드릴게요. 다른 횟집에는 이미 회가 포장되어 판매되는 곳이 있는데 그런 회들은 언제 떠진지도 모르고 신선도가 떨어질 수 있어요. 남해수산이랑 올레수산은 주문이 들어오면 바로 회를 떠주신다고 해요. 가격도 싸고요! "
                                ,
                                List.of(
                                        TempSpotImageResponse.of(1L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-1+%EB%8F%99%EB%AC%B8%EC%8B%9C%EC%9E%A5/%E1%84%83%E1%85%A9%E1%86%BC%E1%84%86%E1%85%AE%E1%86%AB+12.jpeg"),
                                        TempSpotImageResponse.of(2L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-1+%EB%8F%99%EB%AC%B8%EC%8B%9C%EC%9E%A5/%E1%84%83%E1%85%A9%E1%86%BC%E1%84%86%E1%85%AE%E1%86%AB%E1%84%89%E1%85%B5%E1%84%8C%E1%85%A1%E1%86%BC+2.jpeg"),
                                        TempSpotImageResponse.of(3L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-1+%EB%8F%99%EB%AC%B8%EC%8B%9C%EC%9E%A5/%E1%84%83%E1%85%A9%E1%86%BC%E1%84%86%E1%85%AE%E1%86%AB%E1%84%89%E1%85%B5%E1%84%8C%E1%85%A1%E1%86%BC+3.jpeg"),
                                        TempSpotImageResponse.of(4L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-1+%EB%8F%99%EB%AC%B8%EC%8B%9C%EC%9E%A5/%E1%84%83%E1%85%A9%E1%86%BC%E1%84%86%E1%85%AE%E1%86%AB%E1%84%89%E1%85%B5%E1%84%8C%E1%85%A1%E1%86%BC+4.jpeg"),
                                        TempSpotImageResponse.of(5L,
                                                "https://bemyplan-deploy.s3.ap-northeast-2.amazonaws.com/%EB%A6%B0%EC%A7%80/1-1+%EB%8F%99%EB%AC%B8%EC%8B%9C%EC%9E%A5/%E1%84%83%E1%85%A9%E1%86%BC%E1%84%86%E1%85%AE%E1%86%AB%E1%84%89%E1%85%B5%E1%84%8C%E1%85%A1%E1%86%BC+5.jpeg")
                                )
                        )
                )
        );
    }
}
