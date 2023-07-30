package com.project.doday.service;

import com.project.doday.domain.Admin;
import com.project.doday.domain.Member;
import com.project.doday.domain.Solution;
import com.project.doday.domain.SolutionState;
import com.project.doday.dto.SolutionDetailRes;
import com.project.doday.dto.SolutionListRes;
import com.project.doday.dto.SolutionReq;
import com.project.doday.repository.MemberRepository;
import com.project.doday.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SolutionService {
    private final SolutionRepository solutionRepository;
    private final MemberRepository memberRepository;

    /**
     * 신고 해결 신청하기
     */
    public Solution applySolution(Long solutionId, Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Solution save = solutionRepository.save(new Solution());
        save.setMember(member);
        save.setState(SolutionState.RESOLVING);
        return save;
    }

    /**
     * 해결한 문제 보고하기
     */
    public Solution saveSolution(Long memberId, SolutionReq solutionReq) {
        Member member = memberRepository.findById(memberId).get();
        Solution save = solutionRepository.save(solutionReq.toEntity());
        save.setMember(member);
        return save;
    }

    /**
     * 전체 해결 목록 확인
     */
    public List<Solution> getSolutionList() {
        List<Solution> solutions = solutionRepository.findAll();
        return solutions;
    }


    /**
     * 해결내역 상세보기
     */
    public Solution getSolution(Long solutionId) {
        Solution solution = solutionRepository.findById(solutionId).get();
        return solution;
    }

}
