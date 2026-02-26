package com.growthhub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsightDto {

    private String type; // "user" | "global"
    private List<String> insights; // e.g. "Best study window: 9-11 AM"
    private Object data; // optional structured data
}
