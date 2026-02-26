package com.growthhub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaderboardEntryDto {

    private int rank;
    private java.util.UUID userId;
    private String displayName; // no email for privacy
    private Object value; // score, count, etc. depending on leaderboard type
}
