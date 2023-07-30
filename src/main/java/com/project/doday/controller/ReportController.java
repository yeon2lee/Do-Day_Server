package com.project.doday.controller;

import com.project.doday.domain.Report;
import com.project.doday.domain.Solution;
import com.project.doday.dto.ReportFindAllRes;
import com.project.doday.dto.ReportReq;
import com.project.doday.service.ReportService;
import com.project.doday.service.SolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    /**
     * 새로운 신고하기
     */
    @PostMapping("")
    public ResponseEntity<Report> createReport(@RequestBody ReportReq reportReq){
        Long report = reportService.createReport(reportReq);
        return new ResponseEntity(report, HttpStatus.OK);
    }



    /**
     * 신고 전체 목록 보기 - 미해결
     */
    @GetMapping("")
    public ResponseEntity<ArrayList<ReportFindAllRes>> findAllReport(){
        ArrayList<ReportFindAllRes> allReport = reportService.findAllReport();
        return new ResponseEntity(allReport, HttpStatus.OK);
    }

}
