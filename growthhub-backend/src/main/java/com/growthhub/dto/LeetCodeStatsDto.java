package com.growthhub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeetCodeStatsDto {

    private int easySolved;
    private int mediumSolved;
    private int hardSolved;
    private int totalSolved;
    private int streak;
}
