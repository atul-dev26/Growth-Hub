package com.growthhub.service;

import com.growthhub.entity.User;
import com.growthhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserRepository userRepository;

    public Optional<User> getCurrentUser(OAuth2User principal) {
        if (principal == null) return Optional.empty();
        String email = principal.getAttribute("email");
        if (email == null) {
            Object e = principal.getAttributes().get("email");
            email = e != null ? e.toString() : null;
        }
        if (email == null) return Optional.empty();
        return userRepository.findByEmail(email);
    }

    public Optional<java.util.UUID> getCurrentUserId(OAuth2User principal) {
        return getCurrentUser(principal).map(User::getId);
    }
}
