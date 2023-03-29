package com.deploy.bemyplan.preview.controller;

import com.deploy.bemyplan.preview.service.PreviewService;
import com.deploy.bemyplan.preview.service.dto.PreviewContentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PreviewController {

    private final PreviewService previewService;

    @GetMapping("/v2/previews")
    public PreviewContentListResponse getPreviewContent(@RequestParam("planId") final Long planId) {
        return previewService.getPreviewContent(planId);
    }
}
