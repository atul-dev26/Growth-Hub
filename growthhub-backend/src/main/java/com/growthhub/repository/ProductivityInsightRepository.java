package com.growthhub.repository;

import com.growthhub.entity.ProductivityInsight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductivityInsightRepository extends JpaRepository<ProductivityInsight, Long> {

    Optional<ProductivityInsight> findFirstByUserIdOrderByComputedAtDesc(UUID userId);

    Optional<ProductivityInsight> findFirstByScopeOrderByComputedAtDesc(String scope);
}
