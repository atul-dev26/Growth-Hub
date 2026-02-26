package com.growthhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Dashboard returns ONLY summary data (counts, aggregates). Detail pages use dedicated APIs. */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardSummaryDto {

    private DsaSummaryDto dsa;
    private LeetCodeSummaryDto leetcode;
    private GitHubSummaryDto github;
    private ProductivitySummaryDto productivity;
    private UserPublicDto me;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DsaSummaryDto {
        private int completed;
        private int total;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LeetCodeSummaryDto {
        private boolean linked;
        private int totalSolved;
        private int streak;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GitHubSummaryDto {
        private boolean linked;
        private int publicRepos;
        private int contributionCount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductivitySummaryDto {
        private int todayScore;
        private int todayGoalsCompleted;
        private int todayGoalsTotal;
        private int weekCompletionPercent;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserPublicDto {
        private Long id;
        private String displayName;
        private String email;
    }
}
