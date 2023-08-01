package com.project.doday.controller;

import com.project.doday.domain.Report;
import com.project.doday.dto.ReportDetailRes;
import com.project.doday.dto.ReportFindAllRes;
import com.project.doday.dto.ReportReq;
import com.project.doday.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@Tag(name = "신고 API", description = "신고 관련 API")
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    /**
     * 새로운 신고하기
     */
    @Operation(summary = "새로운 신고하기 API", description = "사용자가 새로운 신고를 할 때 사용되는 API입니다.")
    @PostMapping(value="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Report> createReport(@ModelAttribute ReportReq reportReq){
        Long report = reportService.createReport(reportReq);
        return new ResponseEntity(report, HttpStatus.OK);
    }



    /**
     * 신고 전체 목록 보기 - 사용자 (미해결)
     */
    @Operation(summary = "신고 전체 목록 보기 - 사용자 (미해결) API", description = "사용자가 전체 신고를 볼 때 사용되는 API입니다.")
    @GetMapping("")
    public ArrayList<ReportFindAllRes> findAllReport(){
        ArrayList<ReportFindAllRes> allReport = reportService.findAllReport();
        return allReport;
    }

    /**
     * 전체 신고 목록 확인 - 관리자 (승인 전)
     */
    @Operation(summary = "신고 전체 목록 보기 - 관리자 (승인 전) API", description = "관리자가 전체 신고를 볼 때 사용되는 API입니다.")
    @GetMapping("/admin")
    public ArrayList<ReportFindAllRes> findAdminReport(){
        ArrayList<ReportFindAllRes> adminAllReport = reportService.findAdminReport();
        return adminAllReport;
    }

    /**
     * 신고 상세보기
     */
    @Operation(summary = "신고 상세보기 API", description = "신고 상세페이지를 볼 때 사용되는 API입니다.")
    @GetMapping("{reportId}")
    @Parameter(name = "reportId", description = "신고 고유 id값")
    public ReportDetailRes getReport(@PathVariable Long reportId) {
        ReportDetailRes report = reportService.getReport(reportId);
        return report;
    }

}
