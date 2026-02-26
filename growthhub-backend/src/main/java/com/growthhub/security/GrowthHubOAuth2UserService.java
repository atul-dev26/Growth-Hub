package com.growthhub.security;

import com.growthhub.entity.AuthProvider;
import com.growthhub.entity.DsaProgress;
import com.growthhub.entity.User;
import com.growthhub.repository.DsaProgressRepository;
import com.growthhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * Load or create user on OAuth2 login (Google + GitHub).
 */
@Service
@RequiredArgsConstructor
public class GrowthHubOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final DsaProgressRepository dsaProgressRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        AuthProvider provider = "google".equalsIgnoreCase(registrationId) ? AuthProvider.GOOGLE : AuthProvider.GITHUB;
        final String subject = oauth2User.getAttribute("sub") != null
                ? oauth2User.getAttribute("sub")
                : String.valueOf(oauth2User.getAttribute("id"));
        userRepository.findByAuthProviderAndOauthSubject(provider, subject)
                .orElseGet(() -> createUser(oauth2User, provider, subject));
        return oauth2User;
    }

    private User createUser(OAuth2User oauth2User, AuthProvider provider, String subject) {
        String email = oauth2User.getAttribute("email");
        if (email == null) {
            Object e = oauth2User.getAttributes().get("email");
            email = e != null ? e.toString() : null;
        }
        if (email == null) {
            email = subject + "@" + provider.name().toLowerCase() + ".local";
        }
        String name = oauth2User.getAttribute("name");
        if (name == null) {
            name = oauth2User.getAttribute("login");
        }
        if (name == null) {
            name = email;
        }
        User user = User.builder()
                .name(name)
                .email(email)
                .authProvider(provider)
                .oauthSubject(subject)
                .build();
        user = userRepository.save(user);
        dsaProgressRepository.save(DsaProgress.builder()
                .user(user)
                .totalQuestions(456)
                .solvedQuestions(0)
                .lastUpdated(Instant.now())
                .build());
        return user;
    }
}
