package com.growthhub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "github_activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GitHubActivity extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "public_repos")
    private Integer publicRepos = 0;

    @Column(name = "contribution_count")
    private Integer contributionCount = 0;

    @Column(name = "last_fetched_at")
    private java.time.Instant lastFetchedAt;
}
