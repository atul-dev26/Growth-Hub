package com.growthhub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyEfficiencyDto {

    private LocalDate date;
    private int score;
    private int goalsCompleted;
    private int goalsTotal;
}
