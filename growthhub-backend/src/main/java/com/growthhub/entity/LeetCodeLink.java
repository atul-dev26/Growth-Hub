package com.growthhub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leetcode_links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeetCodeLink extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "leetcode_username", nullable = false, length = 128)
    private String leetcodeUsername;
}
