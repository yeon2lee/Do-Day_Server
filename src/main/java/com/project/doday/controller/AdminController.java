package com.project.doday.controller;

import com.project.doday.domain.Report;
import com.project.doday.domain.Solution;
import com.project.doday.dto.ReportFindAllRes;
import com.project.doday.dto.ReportRejectReq;
import com.project.doday.dto.SolutionRejectReq;
import com.project.doday.dto.SolutionReq;
import com.project.doday.repository.AdminRepository;
import com.project.doday.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@Tag(name = "관리자 API", description = "관리자 관련 API")
public class AdminController {
    private final AdminService adminService;

    /**
     * 해결한 신고 승인하기
     */
    @Operation(summary = "해결한 신고 승인하기 API", description = "관리자가 해결을 승인할 때 사용되는 API입니다.")
    @Parameter(name = "solutionId", description = "해결 고유 id값")
    @Parameter(name = "adminId", description = "관리자 고유 id값")
    @PutMapping("/solution/approval/{solutionId}/{adminId}")
    public ResponseEntity<Solution> approvalSolution(@PathVariable Long solutionId, @PathVariable Long adminId) {
        Solution solution = adminService.approveSolution(solutionId, adminId);
        return new ResponseEntity(solution, HttpStatus.OK);
    }

    /**
     * 해결한 신고 반려하기
     */
    @Operation(summary = "해결한 신고 반려하기 API", description = "관리자가 해결을 반려시킬 때 사용되는 API입니다.")
    @Parameter(name = "solutionId", description = "해결 고유 id값")
    @Parameter(name = "adminId", description = "관리자 고유 id값")
    @PutMapping("/solution/reject/{solutionId}/{adminId}")
    public ResponseEntity<Solution> rejectSolution(@PathVariable Long solutionId, @PathVariable Long adminId, @RequestBody SolutionRejectReq solutionRejectReq) {
        Solution solution = adminService.rejectSolution(solutionId, adminId, solutionRejectReq);
        return new ResponseEntity(solution, HttpStatus.OK);
    }

    /**
     * 새로운 신고 승인하기
     */
    @Operation(summary = "새로운 신고 승인하기 API", description = "관리자가 새로운 신고를 승인할 때 사용되는 API입니다.")
    @PutMapping("/report/approval/{reportId}")
    @Parameter(name = "reportId", description = "신고 고유 id값")
    public ResponseEntity<Report> approvalReport(@PathVariable Long reportId) {
        Report report = adminService.approveReport(reportId);
        return new ResponseEntity(report, HttpStatus.OK);
    }

    /**
     * 새로운 신고 반려하기
     */
    @Operation(summary = "새로운 신고 반려하기 API", description = "관리자가 새로운 신고를 반려할 때 사용되는 API입니다.")
    @PutMapping("/report/reject/{reportId}")
    @Parameter(name = "reportId", description = "신고 고유 id값")
    public ResponseEntity<Solution> rejectSolution(@PathVariable Long reportId, @RequestBody ReportRejectReq reportRejectReq) {
        Report report = adminService.rejectReport(reportId, reportRejectReq);
        return new ResponseEntity(report, HttpStatus.OK);
    }
}
