package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import com.growthhub.dto.UserProfileDto;
import com.growthhub.entity.User;
import com.growthhub.service.CurrentUserService;
import com.growthhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CurrentUserService currentUserService;

    /** Get user profile (detailed for profile page). Public by id. */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfileDto>> getUserById(@PathVariable UUID id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.ok(ApiResponse.ok(userService.toProfileDto(user))))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.<UserProfileDto>error("User not found")));
    }

    /** Get current user's detailed profile. */
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileDto>> getMyProfile(
            @AuthenticationPrincipal OAuth2User principal) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return userService.getDetailedProfile(user)
                .map(dto -> ResponseEntity.ok(ApiResponse.ok(dto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
