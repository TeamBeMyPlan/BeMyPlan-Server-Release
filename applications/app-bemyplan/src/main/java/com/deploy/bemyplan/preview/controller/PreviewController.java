package com.deploy.bemyplan.preview.controller;

import com.deploy.bemyplan.preview.service.PreviewService;
import com.deploy.bemyplan.preview.service.dto.PreviewContentListResponse;
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
    public PreviewContentListResponse getPreviewContent(@Valid @PathVariable Long planId) {
        return previewService.getPreviewContent(planId);
    }
}
