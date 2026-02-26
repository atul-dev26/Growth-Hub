package com.growthhub.controller;

import com.growthhub.dto.ApiResponse;
import com.growthhub.dto.DailyEfficiencyDto;
import com.growthhub.dto.DailyTaskCreateDto;
import com.growthhub.dto.DailyTaskDto;
import com.growthhub.entity.User;
import com.growthhub.service.CurrentUserService;
import com.growthhub.service.DailyTaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/daily")
@RequiredArgsConstructor
public class DailyController {

    private final CurrentUserService currentUserService;
    private final DailyTaskService dailyTaskService;

    @PostMapping("/tasks")
    public ResponseEntity<ApiResponse<DailyTaskDto>> createTask(
            @AuthenticationPrincipal OAuth2User principal,
            @Valid @RequestBody DailyTaskCreateDto dto) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(dailyTaskService.createTask(user, dto)));
    }

    @GetMapping("/tasks/{date}")
    public ResponseEntity<ApiResponse<List<DailyTaskDto>>> getTasks(
            @AuthenticationPrincipal OAuth2User principal,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(dailyTaskService.getTasksForDate(user, date)));
    }

    @PostMapping("/tasks/{id}/complete")
    public ResponseEntity<ApiResponse<DailyTaskDto>> completeTask(
            @AuthenticationPrincipal OAuth2User principal,
            @PathVariable Long id) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(dailyTaskService.completeTask(user, id)));
    }

    @GetMapping("/efficiency/{date}")
    public ResponseEntity<ApiResponse<DailyEfficiencyDto>> getEfficiency(
            @AuthenticationPrincipal OAuth2User principal,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = currentUserService.getCurrentUser(principal)
                .orElseThrow(() -> new org.springframework.security.access.AccessDeniedException("Not authenticated"));
        return ResponseEntity.ok(ApiResponse.ok(dailyTaskService.getEfficiencyForDate(user, date)));
    }
}
