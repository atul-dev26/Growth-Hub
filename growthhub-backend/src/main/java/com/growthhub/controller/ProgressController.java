package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import com.growthhub.dto.DsaDetailDto;
import com.growthhub.dto.DsaSummaryDto;
import com.growthhub.entity.User;
import com.growthhub.service.CurrentUserService;
import com.growthhub.service.DsaProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final CurrentUserService currentUserService;
    private final DsaProgressService dsaProgressService;

    /** Detail page: history, lastUpdated. */
    @GetMapping("/dsa/details")
    public ResponseEntity<ApiResponse<DsaDetailDto>> getDsaDetails(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(dsaProgressService.getDetails(user)));
    }

    @PostMapping("/dsa/increment")
    public ResponseEntity<ApiResponse<DsaSummaryDto>> increment(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(dsaProgressService.increment(user)));
    }

    @PostMapping("/dsa/decrement")
    public ResponseEntity<ApiResponse<DsaSummaryDto>> decrement(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(dsaProgressService.decrement(user)));
    }
}
