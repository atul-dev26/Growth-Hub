package com.growthhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily_efficiency", indexes = {
        @Index(name = "idx_daily_efficiency_user_date", columnList = "user_id, efficiency_date")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyEfficiency extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "efficiency_date", nullable = false)
    private LocalDate efficiencyDate;

    /** Completion-based score (e.g. 0-100) */
    private Integer score = 0;

    @Column(name = "goals_completed")
    private Integer goalsCompleted = 0;

    @Column(name = "goals_total")
    private Integer goalsTotal = 0;
}
