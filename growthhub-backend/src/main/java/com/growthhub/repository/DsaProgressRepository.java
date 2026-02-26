package com.growthhub.repository;

import com.growthhub.entity.DsaProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DsaProgressRepository extends JpaRepository<DsaProgress, Long> {

    Optional<DsaProgress> findByUser_Id(UUID userId);
}
