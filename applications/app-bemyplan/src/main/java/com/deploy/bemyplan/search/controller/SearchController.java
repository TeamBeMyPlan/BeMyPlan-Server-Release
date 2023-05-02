package com.deploy.bemyplan.search.controller;


import com.deploy.bemyplan.config.auth.UserId;
import com.deploy.bemyplan.plan.service.dto.response.PlanSearchResponse;
import com.deploy.bemyplan.search.service.SearchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    @ApiOperation("검색 입력어에 따라 여행 일정 목록을 조회합니다.")
    @GetMapping("/v2/plans/search/{search}")
    public List<PlanSearchResponse> getPlansSearch(@UserId final Long userId, @PathVariable final String search){
        return searchService.getPlansSearch(userId, search);
    }
}
