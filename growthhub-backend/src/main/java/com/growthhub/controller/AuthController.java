package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import com.growthhub.dto.UserSummaryDto;
import com.growthhub.service.CurrentUserService;
import com.growthhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Auth APIs. OAuth login: redirect to GET /oauth2/authorization/google or /oauth2/authorization/github.
 * Public: /auth/**, /health. Secured: all others.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CurrentUserService currentUserService;
    private final UserService userService;

    /** GET /auth/me → summary user info. */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<?>> me(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.ok(ApiResponse.ok(Map.of("authenticated", false)));
        }
        return currentUserService.getCurrentUser(principal)
                .flatMap(userService::getSummaryForDashboard)
                .map(summary -> ResponseEntity.<ApiResponse<?>>ok(ApiResponse.ok(summary)))
                .orElseGet(() -> ResponseEntity.ok(ApiResponse.ok(Map.of(
                        "authenticated", true,
                        "name", principal.getName(),
                        "attributes", principal.getAttributes()
                ))));
    }

    /** POST /auth/logout */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Map<String, String>>> logout() {
        return ResponseEntity.ok(ApiResponse.ok(Map.of("message", "Logged out")));
    }
}
