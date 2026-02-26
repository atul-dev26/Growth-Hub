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
public class DailyTaskDto {

    private Long id;
    private LocalDate taskDate;
    private String title;
    private boolean completed;
    private Integer sortOrder;
}
