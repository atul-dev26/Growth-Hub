package com.growthhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_tasks", indexes = {
        @Index(name = "idx_daily_tasks_user_date", columnList = "user_id, task_date")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyTask extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "task_date", nullable = false)
    private LocalDate taskDate;

    @Column(nullable = false, length = 512)
    private String title;

    private boolean completed = false;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;
}
