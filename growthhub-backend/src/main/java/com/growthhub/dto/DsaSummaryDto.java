package com.growthhub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DsaSummaryDto {

    private int solvedQuestions;
    private int totalQuestions;
}
