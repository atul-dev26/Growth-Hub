package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import com.growthhub.dto.DsaSummaryDto;
import com.growthhub.entity.User;
import com.growthhub.service.CurrentUserService;
import com.growthhub.service.DsaProgressService;
import com.growthhub.service.LeetCodeService;
import com.growthhub.service.GitHubService;
import com.growthhub.service.ProductivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

/**
 * Dashboard APIs – summary only (counts, aggregates). Detail pages use dedicated APIs.
 */
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final CurrentUserService currentUserService;
    private final DsaProgressService dsaProgressService;
    private final LeetCodeService leetCodeService;
    private final GitHubService githubService;
    private final ProductivityService productivityService;

    @GetMapping("/dsa-summary")
    public ResponseEntity<ApiResponse<DsaSummaryDto>> dsaSummary(@AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(dsaProgressService.getSummary(user.getId())));
    }

    @GetMapping("/leetcode-summary")
    public ResponseEntity<ApiResponse<com.growthhub.dto.LeetCodeSummaryDto>> leetcodeSummary(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(leetCodeService.getSummary(user.getId())));
    }

    @GetMapping("/github-summary")
    public ResponseEntity<ApiResponse<com.growthhub.dto.GitHubSummaryDto>> githubSummary(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(githubService.getSummary(user.getId())));
    }

    @GetMapping("/productivity-summary")
    public ResponseEntity<ApiResponse<com.growthhub.dto.ProductivitySummaryDto>> productivitySummary(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(productivityService.getSummary(user.getId())));
    }
}
