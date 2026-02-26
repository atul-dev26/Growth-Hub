package com.growthhub.service;

import com.growthhub.dto.DsaDetailDto;
import com.growthhub.dto.DsaSummaryDto;
import com.growthhub.entity.DsaProgress;
import com.growthhub.entity.User;
import com.growthhub.repository.DsaProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DsaProgressService {

    private final DsaProgressRepository dsaProgressRepository;

    @Transactional(readOnly = true)
    public DsaSummaryDto getSummary(UUID userId) {
        return dsaProgressRepository.findByUser_Id(userId)
                .map(p -> DsaSummaryDto.builder()
                        .solvedQuestions(p.getSolvedQuestions())
                        .totalQuestions(p.getTotalQuestions())
                        .build())
                .orElseGet(() -> DsaSummaryDto.builder().solvedQuestions(0).totalQuestions(456).build());
    }

    @Transactional(readOnly = true)
    public DsaDetailDto getDetails(User user) {
        return dsaProgressRepository.findByUser_Id(user.getId())
                .map(p -> DsaDetailDto.builder()
                        .solvedQuestions(p.getSolvedQuestions())
                        .totalQuestions(p.getTotalQuestions())
                        .lastUpdated(p.getLastUpdated())
                        .history(new ArrayList<>()) // TODO: add history table if needed
                        .build())
                .orElseGet(() -> DsaDetailDto.builder()
                        .solvedQuestions(0).totalQuestions(456).history(List.of()).build());
    }

    @Transactional
    public DsaSummaryDto increment(User user) {
        DsaProgress p = getOrCreate(user);
        p.setSolvedQuestions(Math.min(p.getSolvedQuestions() + 1, p.getTotalQuestions()));
        p.setLastUpdated(Instant.now());
        p = dsaProgressRepository.save(p);
        return DsaSummaryDto.builder()
                .solvedQuestions(p.getSolvedQuestions())
                .totalQuestions(p.getTotalQuestions())
                .build();
    }

    @Transactional
    public DsaSummaryDto decrement(User user) {
        DsaProgress p = getOrCreate(user);
        p.setSolvedQuestions(Math.max(0, p.getSolvedQuestions() - 1));
        p.setLastUpdated(Instant.now());
        p = dsaProgressRepository.save(p);
        return DsaSummaryDto.builder()
                .solvedQuestions(p.getSolvedQuestions())
                .totalQuestions(p.getTotalQuestions())
                .build();
    }

    private DsaProgress getOrCreate(User user) {
        return dsaProgressRepository.findByUser_Id(user.getId())
                .orElseGet(() -> dsaProgressRepository.save(DsaProgress.builder()
                        .user(user)
                        .totalQuestions(456)
                        .solvedQuestions(0)
                        .lastUpdated(Instant.now())
                        .build()));
    }
}
