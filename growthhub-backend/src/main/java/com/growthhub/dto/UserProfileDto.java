package com.growthhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/** Detailed user profile for profile page. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {

    private UUID id;
    private String name;
    private String email;
    private String authProvider;
    private String githubUsername;
    private String leetcodeUsername;
    private String role;
    private boolean leaderboardOptIn;
    private Instant createdAt;
    private Instant updatedAt;
}
