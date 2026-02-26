package com.growthhub.repository;

import com.growthhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByAuthProviderAndOauthSubject(com.growthhub.entity.AuthProvider provider, String subject);

    boolean existsByEmail(String email);
}
