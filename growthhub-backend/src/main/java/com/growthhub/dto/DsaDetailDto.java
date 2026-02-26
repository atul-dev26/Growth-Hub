package com.growthhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DsaDetailDto {

    private int solvedQuestions;
    private int totalQuestions;
    private Instant lastUpdated;
    private List<DsaHistoryEntryDto> history;
}
