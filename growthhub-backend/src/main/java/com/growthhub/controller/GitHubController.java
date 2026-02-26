package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import com.growthhub.entity.User;
import com.growthhub.service.CurrentUserService;
import com.growthhub.service.GitHubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class GitHubController {

    private final CurrentUserService currentUserService;
    private final GitHubService githubService;

    /** Detail page: repos, languages, contribution summary. */
    @GetMapping("/details")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getDetails(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(githubService.getDetails(user)));
    }
}
