package com.growthhub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    /** JSON or comma-separated: which tracks to show (dsa, leetcode, github, productivity) */
    @Column(name = "track_selection", length = 256)
    private String trackSelection;

    /** Schedule/timetable preferences (e.g. JSON: daily goals, time slots) */
    @Column(name = "schedule_preferences", columnDefinition = "TEXT")
    private String schedulePreferences;

    /** General preferences JSON */
    @Column(columnDefinition = "TEXT")
    private String preferences;
}
