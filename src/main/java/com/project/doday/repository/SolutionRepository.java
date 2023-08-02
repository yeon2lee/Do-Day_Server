package com.project.doday.repository;

import com.project.doday.domain.Solution;
import com.project.doday.domain.SolutionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    // state가 해결완료(RESOLVED)인 column을 가장 우선으로 하고, 나머지는 최신순으로 정렬
    @Query("SELECT s FROM Solution s ORDER BY CASE WHEN (s.state = 'RESOLVED') THEN 1 ELSE 2 END, s.createdDate DESC")
    ArrayList<Solution> findAllPriority();

    ArrayList<Solution> findAllByOrderByCreatedDateDesc();
}
