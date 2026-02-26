package com.growthhub.service;

import com.growthhub.dto.LeaderboardEntryDto;
import com.growthhub.entity.User;
import com.growthhub.repository.DailyEfficiencyRepository;
import com.growthhub.repository.DsaProgressRepository;
import com.growthhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final UserRepository userRepository;
    private final DsaProgressRepository dsaProgressRepository;
    private final DailyEfficiencyRepository dailyEfficiencyRepository;

    @Transactional
    public void optIn(User user) {
        user.setLeaderboardOptIn(true);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<LeaderboardEntryDto> getWeeklyConsistency(User currentUser) {
        if (!currentUser.isLeaderboardOptIn()) return List.of();
        LocalDate weekStart = LocalDate.now().minusDays(7);
        return getLeaderboardByEfficiencyScore(weekStart, LocalDate.now(), 10);
    }

    @Transactional(readOnly = true)
    public List<LeaderboardEntryDto> getImprovement(User currentUser) {
        if (!currentUser.isLeaderboardOptIn()) return List.of();
        return getTotalSolved(currentUser);
    }

    @Transactional(readOnly = true)
    public List<LeaderboardEntryDto> getTotalSolved(User currentUser) {
        if (!currentUser.isLeaderboardOptIn()) return List.of();
        List<LeaderboardEntryDto> result = new ArrayList<>();
        int rank = 1;
        var progressList = dsaProgressRepository.findAll().stream()
                .filter(p -> p.getUser().isLeaderboardOptIn())
                .sorted((a, b) -> Integer.compare(b.getSolvedQuestions(), a.getSolvedQuestions()))
                .limit(10)
                .toList();
        for (var p : progressList) {
            User u = p.getUser();
            result.add(LeaderboardEntryDto.builder()
                    .rank(rank++)
                    .userId(u.getId())
                    .displayName(u.getName())
                    .value(p.getSolvedQuestions())
                    .build());
        }
        return result;
    }

    private List<LeaderboardEntryDto> getLeaderboardByEfficiencyScore(LocalDate from, LocalDate to, int limit) {
        List<LeaderboardEntryDto> list = new ArrayList<>();
        Set<UUID> users = userRepository.findAll().stream()
                .filter(User::isLeaderboardOptIn)
                .map(User::getId)
                .collect(Collectors.toSet());
        var efficiencies = dailyEfficiencyRepository.findAll().stream()
                .filter(e -> !e.getEfficiencyDate().isBefore(from) && !e.getEfficiencyDate().isAfter(to))
                .filter(e -> users.contains(e.getUser().getId()))
                .collect(Collectors.groupingBy(e -> e.getUser().getId()));
        int rank = 1;
        var sorted = efficiencies.entrySet().stream()
                .map(e -> {
                    int avgScore = (int) e.getValue().stream().mapToInt(com.growthhub.entity.DailyEfficiency::getScore).average().orElse(0);
                    var u = e.getValue().get(0).getUser();
                    return new Object[]{u.getId(), u.getName(), avgScore};
                })
                .sorted((a, b) -> Integer.compare((Integer) b[2], (Integer) a[2]))
                .limit(limit)
                .toList();
        for (Object[] row : sorted) {
            list.add(LeaderboardEntryDto.builder()
                    .rank(rank++)
                    .userId((UUID) row[0])
                    .displayName((String) row[1])
                    .value(row[2])
                    .build());
        }
        return list;
    }
}
