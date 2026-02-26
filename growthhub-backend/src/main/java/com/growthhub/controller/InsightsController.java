package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import com.growthhub.dto.InsightDto;
import com.growthhub.entity.User;
import com.growthhub.service.CurrentUserService;
import com.growthhub.service.InsightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insights")
@RequiredArgsConstructor
public class InsightsController {

    private final CurrentUserService currentUserService;
    private final InsightsService insightsService;

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<InsightDto>> getUserInsights(@AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(insightsService.getUserInsights(user)));
    }

    @GetMapping("/global")
    public ResponseEntity<ApiResponse<InsightDto>> getGlobalInsights(@AuthenticationPrincipal OAuth2User principal) {
        currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(insightsService.getGlobalInsights()));
    }
}
