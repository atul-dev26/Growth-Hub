package com.growthhub.service;

import com.growthhub.dto.LeetCodeStatsDto;
import com.growthhub.dto.LeetCodeSummaryDto;
import com.growthhub.entity.LeetCodeStats;
import com.growthhub.entity.User;
import com.growthhub.repository.LeetCodeStatsRepository;
import com.growthhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeetCodeService {

    private final LeetCodeStatsRepository leetCodeStatsRepository;
    private final UserRepository userRepository;

    /** Dashboard: totalSolved only. */
    @Transactional(readOnly = true)
    public LeetCodeSummaryDto getSummary(UUID userId) {
        return leetCodeStatsRepository.findByUser_Id(userId)
                .map(s -> LeetCodeSummaryDto.builder().totalSolved(s.getTotalSolved()).build())
                .orElseGet(() -> LeetCodeSummaryDto.builder().totalSolved(0).build());
    }

    /** Detail: difficulty-wise breakdown + trends. */
    @Transactional(readOnly = true)
    public LeetCodeStatsDto getStats(User user) {
        var stats = leetCodeStatsRepository.findByUser_Id(user.getId());
        if (stats.isEmpty()) {
            return LeetCodeStatsDto.builder()
                    .easySolved(0).mediumSolved(0).hardSolved(0).totalSolved(0).streak(0)
                    .build();
        }
        LeetCodeStats s = stats.get();
        return LeetCodeStatsDto.builder()
                .easySolved(s.getEasySolved())
                .mediumSolved(s.getMediumSolved())
                .hardSolved(s.getHardSolved())
                .totalSolved(s.getTotalSolved())
                .streak(s.getStreak())
                .build();
    }

    @Transactional
    public void link(User user, String leetcodeUsername) {
        user.setLeetcodeUsername(leetcodeUsername.trim());
        userRepository.save(user);
        leetCodeStatsRepository.findByUser_Id(user.getId())
                .orElseGet(() -> leetCodeStatsRepository.save(LeetCodeStats.builder()
                        .user(user)
                        .easySolved(0).mediumSolved(0).hardSolved(0).totalSolved(0).streak(0)
                        .build()));
    }
}
