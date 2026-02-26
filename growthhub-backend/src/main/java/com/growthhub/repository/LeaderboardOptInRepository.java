package com.growthhub.repository;

import com.growthhub.entity.LeaderboardOptIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaderboardOptInRepository extends JpaRepository<LeaderboardOptIn, Long> {

    Optional<LeaderboardOptIn> findByUserId(Long userId);

    boolean existsByUserIdAndOptedInTrue(Long userId);
}
