package com.deploy.bemyplan.controller.post;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.config.interceptor.Auth;
import com.deploy.bemyplan.config.resolver.UserId;
import com.deploy.bemyplan.config.validator.AllowedSortProperties;
import com.deploy.bemyplan.controller.post.dto.request.RetrievePlansRequest;
import com.deploy.bemyplan.service.post.dto.response.PlansScrollResponse;
import com.deploy.bemyplan.service.post.PostRetrieveService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
public class PostRetrieveController {

    private final PostRetrieveService planRetrieveService;

    @Auth
    @GetMapping("/v1/plan/popular")
    public ApiResponse<PlansScrollResponse> getPopularPlans(@UserId Long userId, @Valid RetrievePlansRequest request,
                                                            @AllowedSortProperties({"id", "orderCnt"}) Pageable pageable) {
        return ApiResponse.success(planRetrieveService.retrievePopularPlans(userId, request.getSize(), request.getLastPlanId(), pageable));
    }
}