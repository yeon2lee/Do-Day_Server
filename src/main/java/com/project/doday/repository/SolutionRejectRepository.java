package com.project.doday.repository;

import com.project.doday.domain.SolutionReject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SolutionRejectRepository extends JpaRepository<SolutionReject, Long> {
    Optional<SolutionReject> findBySolutionId(Long solutionId);
}
