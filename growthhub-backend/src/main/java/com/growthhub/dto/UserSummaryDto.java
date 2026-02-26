package com.growthhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/** Summary user info for dashboard / auth/me. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSummaryDto {

    private UUID id;
    private String name;
    private String email;
    private String authProvider;
    private boolean leaderboardOptIn;
}
