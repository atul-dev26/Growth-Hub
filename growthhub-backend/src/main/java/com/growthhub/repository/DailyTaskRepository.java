package com.growthhub.repository;

import com.growthhub.entity.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {

    List<DailyTask> findByUser_IdAndTaskDateOrderBySortOrderAsc(UUID userId, LocalDate taskDate);

    List<DailyTask> findByUser_IdAndTaskDate(UUID userId, LocalDate taskDate);
}
