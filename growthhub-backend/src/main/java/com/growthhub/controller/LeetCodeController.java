package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import com.growthhub.dto.LeetCodeLinkDto;
import com.growthhub.dto.LeetCodeStatsDto;
import com.growthhub.entity.User;
import com.growthhub.service.CurrentUserService;
import com.growthhub.service.LeetCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leetcode")
@RequiredArgsConstructor
public class LeetCodeController {

    private final CurrentUserService currentUserService;
    private final LeetCodeService leetCodeService;

    @PostMapping("/link")
    public ResponseEntity<ApiResponse<String>> link(
            @AuthenticationPrincipal OAuth2User principal,
            @RequestBody LeetCodeLinkDto dto) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        if (dto.getLeetcodeUsername() == null || dto.getLeetcodeUsername().isBlank()) {
            return ResponseEntity.badRequest().body(ApiResponse.error("leetcodeUsername is required"));
        }
        leetCodeService.link(user, dto.getLeetcodeUsername());
        return ResponseEntity.ok(ApiResponse.ok("Linked"));
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<LeetCodeStatsDto>> getStats(@AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(leetCodeService.getStats(user)));
    }
}
