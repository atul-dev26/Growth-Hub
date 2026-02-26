package com.growthhub.repository;

import com.growthhub.entity.LeetCodeStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LeetCodeStatsRepository extends JpaRepository<LeetCodeStats, Long> {

    Optional<LeetCodeStats> findByUser_Id(UUID userId);
}
