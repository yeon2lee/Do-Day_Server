package com.project.doday.controller;

import com.project.doday.domain.Solution;
import com.project.doday.dto.SolutionDetailRes;
import com.project.doday.dto.SolutionListRes;
import com.project.doday.dto.SolutionReq;
import com.project.doday.service.SolutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @Operation(summary = "신고 해결 신청하기 API", description = "신고를 해결하고자 할 때 사용되는 신청 API입니다.")
    @PostMapping("/solution/{reportId}/{memberId}")
    @Parameter(name = "reportId", description = "신고 고유 id값")
    @Parameter(name = "memberId", description = "내 고유 id값")
    public ResponseEntity<Solution> applySolution(@PathVariable Long reportId, @PathVariable Long memberId) {
        Solution solution = solutionService.applySolution(reportId, memberId);
        return new ResponseEntity(solution, HttpStatus.OK);
    }

    /**
     * 해결한 문제 보고하기
     */
    @Operation(summary = "해결한 문제 보고하기 API", description = "해결한 문제를 보고할 때 사용되는 API입니다.")
    @PostMapping(value="/solution/{solutionId}/{memberId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Parameter(name = "solutionId", description = "해결 고유 id값")
    @Parameter(name = "memberId", description = "내 고유 id값")
    public ResponseEntity<Solution> reportSolution(@PathVariable Long solutionId, @PathVariable Long memberId,
                                                   @ModelAttribute SolutionReq solutionReq) {
        Solution solution = solutionService.reportSolution(solutionId, memberId, solutionReq);
        // TODO 추후에 예외처리 추가
        return new ResponseEntity(solution, HttpStatus.OK);
    }

    /**
     * 전체 해결 목록 확인
     */
    @Operation(summary = "해결 전체 목록 보기 - 관리자 API", description = "관리자가 전체 해결 목록을 볼 때  사용되는 API입니다.")
    @GetMapping("/solution")
    public List<SolutionListRes> getSolutionList() {
        List<SolutionListRes> solutionList = solutionService.getSolutionList();
        return solutionList;
    }

    /**
     * 해결내역 상세보기
     */
    @Operation(summary = "해결 내역 상세보기 API", description = "해결 상세페이지를 볼 때 사용되는 API입니다.")
    @GetMapping("solution/{solutionId}")
    @Parameter(name = "solutionId", description = "해결 고유 id값")
    public SolutionDetailRes getSolution(@PathVariable Long solutionId) {
        SolutionDetailRes solution = solutionService.getSolution(solutionId);
        return solution;
    }
}
