package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import com.growthhub.dto.LeaderboardEntryDto;
import com.growthhub.entity.User;
import com.growthhub.service.CurrentUserService;
import com.growthhub.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final CurrentUserService currentUserService;
    private final LeaderboardService leaderboardService;

    @PostMapping("/opt-in")
    public ResponseEntity<ApiResponse<String>> optIn(@AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        leaderboardService.optIn(user);
        return ResponseEntity.ok(ApiResponse.ok("Opted in"));
    }

    @GetMapping("/weekly-consistency")
    public ResponseEntity<ApiResponse<List<LeaderboardEntryDto>>> weeklyConsistency(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(leaderboardService.getWeeklyConsistency(user)));
    }

    @GetMapping("/improvement")
    public ResponseEntity<ApiResponse<List<LeaderboardEntryDto>>> improvement(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(leaderboardService.getImprovement(user)));
    }

    @GetMapping("/total-solved")
    public ResponseEntity<ApiResponse<List<LeaderboardEntryDto>>> totalSolved(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(leaderboardService.getTotalSolved(user)));
    }
}
