package com.deploy.bemyplan.controller.preview;

import com.deploy.bemyplan.common.dto.ApiResponse;
import com.deploy.bemyplan.service.preview.PreviewService;
import com.deploy.bemyplan.service.preview.dto.PreviewContentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PreviewController {

    private final PreviewService previewService;

    @GetMapping("/v2/preview/{planId}")
    public ApiResponse<PreviewContentListResponse> getPreviewContent(@Valid @PathVariable Long planId) {
        return ApiResponse.success(previewService.getPreviewContent(planId));
    }
}
