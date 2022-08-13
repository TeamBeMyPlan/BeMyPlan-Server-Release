package com.deploy.bemyplan.controller.user;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.service.user.UserService;
import com.deploy.bemyplan.service.user.dto.response.CreatorInfoResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @ApiOperation("크리에이터 조회 - 크리에이터의 정보를 조회합니다.")
    @GetMapping("/v2/user/{planId}")
    public ApiResponse<CreatorInfoResponse> getSpotMoveInfos(@PathVariable Long planId) {
        return ApiResponse.success(userService.getCreatorInfo(planId));
    }

}
