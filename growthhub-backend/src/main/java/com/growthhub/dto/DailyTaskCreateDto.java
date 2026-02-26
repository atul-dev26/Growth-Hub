package com.growthhub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyTaskCreateDto {

    private LocalDate taskDate; // optional; default today
    @NotBlank
    private String title;
    private Integer sortOrder;
}
