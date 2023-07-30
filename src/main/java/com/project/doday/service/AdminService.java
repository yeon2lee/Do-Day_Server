package com.project.doday.service;

import com.project.doday.domain.Admin;
import com.project.doday.domain.Member;
import com.project.doday.domain.Solution;
import com.project.doday.domain.SolutionState;
import com.project.doday.dto.SolutionReq;
import com.project.doday.repository.AdminRepository;
import com.project.doday.repository.MemberRepository;
import com.project.doday.repository.ReportRepository;
import com.project.doday.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final SolutionRepository solutionRepository;

    /**
     * 해결한 신고 승인하기
     */
    public Solution approveSolution(Long solutionId) {
        Solution solution = solutionRepository.findById(solutionId).get();
        solution.setState(SolutionState.CONFIRMED);
        return solution;
    }

    /**
     * 해결한 신고 반려하기
     */
    public Solution rejectSolution(Long solutionId) {
        Solution solution = solutionRepository.findById(solutionId).get();
        solution.setState(SolutionState.REJECTED);
        // TODO: report의 state를 해결완료(RESOLVED)에서 미해결(UNRESOLVED)로 변경
        return solution;
    }
}
