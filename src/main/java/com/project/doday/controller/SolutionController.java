package com.project.doday.controller;

import com.project.doday.domain.Solution;
import com.project.doday.dto.SolutionDetailRes;
import com.project.doday.dto.SolutionListRes;
import com.project.doday.dto.SolutionReq;
import com.project.doday.service.SolutionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "해결하기 API", description = "해결 관련 API")
public class SolutionController {
    private final SolutionService solutionService;

    /**
     * 신고 해결 신청하기
     */
    @PostMapping("/solution/{reportId}/{memberId}")
    public ResponseEntity<Solution> applySolution(@PathVariable Long reportId, @PathVariable Long memberId) {
        Solution solution = solutionService.applySolution(reportId, memberId);
        return new ResponseEntity(solution, HttpStatus.OK);
    }

    /**
     * 해결한 문제 보고하기
     */
    @PutMapping("/solution/{solutionId}/{memberId}")
    public ResponseEntity<Solution> reportSolution(@PathVariable Long solutionId, @PathVariable Long memberId,
                                                   @RequestBody SolutionReq solutionReq) {
        Solution solution = solutionService.reportSolution(solutionId, memberId, solutionReq);
        //TODO 추후에 예외처리 추가
        return new ResponseEntity(solutionReq, HttpStatus.OK);
    }

    /**
     * 전체 해결 목록 확인
     */
    @GetMapping("/solution")
    public List<SolutionListRes> getSolutionList() {
        List<SolutionListRes> solutionList = solutionService.getSolutionList();
        return solutionList;
    }

    /**
     * 해결내역 상세보기
     */
    @GetMapping("solution/{solutionId}")
    public SolutionDetailRes getSolution(@PathVariable Long solutionId) {
        SolutionDetailRes solution = solutionService.getSolution(solutionId);
        return solution;
    }
}
