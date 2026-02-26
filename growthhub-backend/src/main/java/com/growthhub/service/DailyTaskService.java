package com.growthhub.service;

import com.growthhub.dto.DailyEfficiencyDto;
import com.growthhub.dto.DailyTaskCreateDto;
import com.growthhub.dto.DailyTaskDto;
import com.growthhub.entity.DailyEfficiency;
import com.growthhub.entity.DailyTask;
import com.growthhub.entity.User;
import com.growthhub.exception.ResourceNotFoundException;
import com.growthhub.repository.DailyEfficiencyRepository;
import com.growthhub.repository.DailyTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;
    private final DailyEfficiencyRepository dailyEfficiencyRepository;

    @Transactional
    public DailyTaskDto createTask(User user, DailyTaskCreateDto dto) {
        LocalDate date = dto.getTaskDate() != null ? dto.getTaskDate() : LocalDate.now();
        int order = dto.getSortOrder() != null ? dto.getSortOrder() : 0;
        DailyTask task = DailyTask.builder()
                .user(user)
                .taskDate(date)
                .title(dto.getTitle().trim())
                .completed(false)
                .sortOrder(order)
                .build();
        task = dailyTaskRepository.save(task);
        return toDto(task);
    }

    @Transactional(readOnly = true)
    public List<DailyTaskDto> getTasksForDate(User user, LocalDate date) {
        return dailyTaskRepository.findByUser_IdAndTaskDateOrderBySortOrderAsc(user.getId(), date)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public DailyTaskDto completeTask(User user, Long taskId) {
        DailyTask task = dailyTaskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", taskId));
        if (!task.getUser().getId().equals(user.getId())) {
            throw new ResourceNotFoundException("Task", taskId);
        }
        task.setCompleted(true);
        task = dailyTaskRepository.save(task);
        updateEfficiencyForDate(user, task.getTaskDate());
        return toDto(task);
    }

    @Transactional(readOnly = true)
    public DailyEfficiencyDto getEfficiencyForDate(User user, LocalDate date) {
        var eff = dailyEfficiencyRepository.findByUser_IdAndEfficiencyDate(user.getId(), date);
        if (eff.isEmpty()) {
            var tasks = dailyTaskRepository.findByUser_IdAndTaskDate(user.getId(), date);
            int total = tasks.size();
            int completed = (int) tasks.stream().filter(DailyTask::isCompleted).count();
            int score = total > 0 ? (completed * 100) / total : 0;
            return DailyEfficiencyDto.builder()
                    .date(date)
                    .score(score)
                    .goalsCompleted(completed)
                    .goalsTotal(total)
                    .build();
        }
        DailyEfficiency e = eff.get();
        return DailyEfficiencyDto.builder()
                .date(e.getEfficiencyDate())
                .score(e.getScore())
                .goalsCompleted(e.getGoalsCompleted())
                .goalsTotal(e.getGoalsTotal())
                .build();
    }

    private void updateEfficiencyForDate(User user, LocalDate date) {
        var tasks = dailyTaskRepository.findByUser_IdAndTaskDate(user.getId(), date);
        int total = tasks.size();
        int completed = (int) tasks.stream().filter(DailyTask::isCompleted).count();
        int score = total > 0 ? (completed * 100) / total : 0;
        DailyEfficiency eff = dailyEfficiencyRepository.findByUser_IdAndEfficiencyDate(user.getId(), date)
                .orElseGet(() -> DailyEfficiency.builder()
                        .user(user)
                        .efficiencyDate(date)
                        .goalsTotal(total)
                        .build());
        eff.setGoalsCompleted(completed);
        eff.setGoalsTotal(total);
        eff.setScore(score);
        dailyEfficiencyRepository.save(eff);
    }

    private DailyTaskDto toDto(DailyTask t) {
        return DailyTaskDto.builder()
                .id(t.getId())
                .taskDate(t.getTaskDate())
                .title(t.getTitle())
                .completed(t.isCompleted())
                .sortOrder(t.getSortOrder())
                .build();
    }
}
