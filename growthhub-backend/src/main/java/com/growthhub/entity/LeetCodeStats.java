package com.growthhub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leetcode_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeetCodeStats extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private Integer easySolved = 0;
    private Integer mediumSolved = 0;
    private Integer hardSolved = 0;
    private Integer totalSolved = 0;
    private Integer streak = 0;

    @Column(name = "last_fetched_at")
    private java.time.Instant lastFetchedAt;
}
