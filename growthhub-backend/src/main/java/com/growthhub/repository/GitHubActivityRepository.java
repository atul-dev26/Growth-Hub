package com.growthhub.repository;

import com.growthhub.entity.GitHubActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GitHubActivityRepository extends JpaRepository<GitHubActivity, Long> {

    Optional<GitHubActivity> findByUser_Id(UUID userId);
}
