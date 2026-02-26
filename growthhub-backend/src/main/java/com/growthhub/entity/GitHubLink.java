package com.growthhub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "github_links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GitHubLink extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "github_username", nullable = false, length = 128)
    private String githubUsername;
}
