package com.growthhub.repository;

import com.growthhub.entity.DailyEfficiency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DailyEfficiencyRepository extends JpaRepository<DailyEfficiency, Long> {

    Optional<DailyEfficiency> findByUser_IdAndEfficiencyDate(UUID userId, LocalDate date);
}
