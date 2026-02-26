package com.growthhub.repository;

import com.growthhub.entity.LeetCodeLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeetCodeLinkRepository extends JpaRepository<LeetCodeLink, Long> {

    Optional<LeetCodeLink> findByUserId(Long userId);
}
