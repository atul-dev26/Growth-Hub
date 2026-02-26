package com.growthhub.service;

import com.growthhub.dto.InsightDto;
import com.growthhub.entity.User;
import com.growthhub.repository.ProductivityInsightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsightsService {

    private final ProductivityInsightRepository productivityInsightRepository;

    @Transactional(readOnly = true)
    public InsightDto getUserInsights(User user) {
        return productivityInsightRepository.findFirstByUserIdOrderByComputedAtDesc(user.getId())
                .map(p -> InsightDto.builder()
                        .type("user")
                        .insights(p.getInsightsJson() != null ? List.of(p.getInsightsJson().split("\n")) : List.of())
                        .data(null)
                        .build())
                .orElseGet(() -> InsightDto.builder()
                        .type("user")
                        .insights(List.of("Complete more tasks to get personalized insights."))
                        .build());
    }

    @Transactional(readOnly = true)
    public InsightDto getGlobalInsights() {
        return productivityInsightRepository.findFirstByScopeOrderByComputedAtDesc("GLOBAL")
                .map(p -> InsightDto.builder()
                        .type("global")
                        .insights(p.getInsightsJson() != null ? List.of(p.getInsightsJson().split("\n")) : List.of())
                        .data(null)
                        .build())
                .orElseGet(() -> InsightDto.builder()
                        .type("global")
                        .insights(List.of("Peak productivity: 10 AM – 12 PM (anonymous aggregate)."))
                        .build());
    }
}
