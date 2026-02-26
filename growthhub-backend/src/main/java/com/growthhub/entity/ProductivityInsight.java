package com.growthhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

/** Precomputed insights (ML or rules). */
@Entity
@Table(name = "productivity_insights", indexes = {
        @Index(name = "idx_productivity_insights_user", columnList = "user_id"),
        @Index(name = "idx_productivity_insights_global", columnList = "scope")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductivityInsight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** USER or GLOBAL */
    @Column(nullable = false, length = 20)
    private String scope;

    @Column(name = "user_id")
    private UUID userId;

    @Column(columnDefinition = "TEXT")
    private String insightsJson;

    @Column(name = "computed_at")
    private Instant computedAt;
}
