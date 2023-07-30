package com.project.doday.service;

import com.project.doday.domain.Solution;
import com.project.doday.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final SolutionRepository solutionRepository;

    /**
     * 나의 해결 목록 보기
     */
    public List<Solution> getMyProcess(Long memberId) {
        List<Solution> solutions = solutionRepository.findAll();
        List<Solution> mySolution = new ArrayList<>();

        for(Solution solution : solutions){
            if(solution.getMember().getId() == memberId)
                mySolution.add(solution);
        }
        return mySolution;
    }

}
