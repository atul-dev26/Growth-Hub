package com.growthhub.repository;

import com.growthhub.entity.GitHubLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GitHubLinkRepository extends JpaRepository<GitHubLink, Long> {

    Optional<GitHubLink> findByUserId(Long userId);
}
