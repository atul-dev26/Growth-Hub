package com.growthhub.service;

import com.growthhub.dto.GitHubSummaryDto;
import com.growthhub.entity.GitHubActivity;
import com.growthhub.entity.User;
import com.growthhub.repository.GitHubActivityRepository;
import com.growthhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GitHubService {

    private final GitHubActivityRepository githubActivityRepository;
    private final UserRepository userRepository;

    /** Dashboard: repo count, recent activity count. */
    @Transactional(readOnly = true)
    public GitHubSummaryDto getSummary(UUID userId) {
        return githubActivityRepository.findByUser_Id(userId)
                .map(a -> GitHubSummaryDto.builder()
                        .repoCount(a.getPublicRepos())
                        .recentActivityCount(a.getContributionCount())
                        .build())
                .orElseGet(() -> GitHubSummaryDto.builder().repoCount(0).recentActivityCount(0).build());
    }

    /** Detail: repos, languages, contribution summary. */
    @Transactional(readOnly = true)
    public Map<String, Object> getDetails(User user) {
        var activity = githubActivityRepository.findByUser_Id(user.getId());
        var u = userRepository.findById(user.getId()).orElse(user);
        return Map.of(
                "username", u.getGithubUsername() != null ? u.getGithubUsername() : "",
                "publicRepos", activity.map(GitHubActivity::getPublicRepos).orElse(0),
                "contributionCount", activity.map(GitHubActivity::getContributionCount).orElse(0)
        );
    }

    @Transactional
    public void link(User user, String githubUsername) {
        user.setGithubUsername(githubUsername.trim());
        userRepository.save(user);
        githubActivityRepository.findByUser_Id(user.getId())
                .orElseGet(() -> githubActivityRepository.save(GitHubActivity.builder()
                        .user(user)
                        .publicRepos(0)
                        .contributionCount(0)
                        .build()));
    }
}
