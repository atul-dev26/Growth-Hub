package com.growthhub.service;

import com.growthhub.dto.UserProfileDto;
import com.growthhub.dto.UserSummaryDto;
import com.growthhub.entity.User;
import com.growthhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /** Create user on first OAuth login is done in GrowthHubOAuth2UserService. */
    @Transactional(readOnly = true)
    public Optional<UserSummaryDto> getSummaryForDashboard(User user) {
        if (user == null) return Optional.empty();
        return Optional.of(toSummaryDto(user));
    }

    @Transactional(readOnly = true)
    public Optional<UserProfileDto> getDetailedProfile(User user) {
        if (user == null) return Optional.empty();
        return Optional.of(toProfileDto(user));
    }

    public UserSummaryDto toSummaryDto(User user) {
        return UserSummaryDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .authProvider(user.getAuthProvider().name())
                .leaderboardOptIn(user.isLeaderboardOptIn())
                .build();
    }

    public UserProfileDto toProfileDto(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .authProvider(user.getAuthProvider().name())
                .githubUsername(user.getGithubUsername())
                .leetcodeUsername(user.getLeetcodeUsername())
                .role(user.getRole().name())
                .leaderboardOptIn(user.isLeaderboardOptIn())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
