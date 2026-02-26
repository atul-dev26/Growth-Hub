package com.growthhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "leaderboard_opt_ins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaderboardOptIn extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "opted_in", nullable = false)
    private boolean optedIn = false;

    @Column(name = "opted_in_at")
    private Instant optedInAt;

    /** Optional participation fee paid at */
    @Column(name = "paid_at")
    private Instant paidAt;
}
