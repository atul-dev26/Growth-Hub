package com.growthhub.service;

import com.growthhub.dto.ProductivitySummaryDto;
import com.growthhub.repository.DailyEfficiencyRepository;
import com.growthhub.repository.DailyTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductivityService {

    private final DailyTaskRepository dailyTaskRepository;
    private final DailyEfficiencyRepository dailyEfficiencyRepository;

    /** Dashboard: today efficiency only. */
    @Transactional(readOnly = true)
    public ProductivitySummaryDto getSummary(UUID userId) {
        LocalDate today = LocalDate.now();
        var eff = dailyEfficiencyRepository.findByUser_IdAndEfficiencyDate(userId, today);
        if (eff.isPresent()) {
            return ProductivitySummaryDto.builder()
                    .todayEfficiency(eff.get().getScore())
                    .build();
        }
        var tasks = dailyTaskRepository.findByUser_IdAndTaskDate(userId, today);
        int total = tasks.size();
        int completed = (int) tasks.stream().filter(t -> t.isCompleted()).count();
        int score = total > 0 ? (completed * 100) / total : 0;
        return ProductivitySummaryDto.builder()
                .todayEfficiency(score)
                .build();
    }
}
