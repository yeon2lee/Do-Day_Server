package com.project.doday.service;

import com.project.doday.domain.*;
import com.project.doday.dto.ReportDetailRes;
import com.project.doday.dto.ReportFindAllRes;
import com.project.doday.dto.ReportReq;
import com.project.doday.repository.AdminRepository;
import com.project.doday.repository.MemberRepository;
import com.project.doday.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final MemberRepository memberRepository;
    private final ReportRepository reportRepository;

    /**
     * 문제 신고하기
     */
    public Long createReport(ReportReq reportReq) {
        Member member = memberRepository.findById(reportReq.getMemberId()).get();

        Report createReport = Report.builder()
                .member(member)
                .latitude(reportReq.getLatitude())
                .longitude(reportReq.getLongitude())
                .location(reportReq.getLocation())
                .photoRaincatch(reportReq.getPhotoRaincatch())
                .photoAround(reportReq.getPhotoAround())
                .description(reportReq.getDescription())
                .state(ReportState.UNAPPROVAL)
                .build();

        System.out.println(createReport.getId());
        System.out.println(createReport.getMember().getUserId());

        reportRepository.save(createReport);

        System.out.println(createReport.getId());
        System.out.println(createReport.getMember().getUserId());

        return createReport.getId();

    }

    /**
     * 신고 목록 보기 - 사용자
     */
    public ArrayList<ReportFindAllRes> findAllReport(){
        ArrayList<ReportFindAllRes> findResult = new ArrayList<ReportFindAllRes>();

        ArrayList<Report> reports = reportRepository.findAllByStateOrderByCreatedDateDesc(ReportState.UNRESOLVED);

        for(Report report: reports) {
            findResult.add(new ReportFindAllRes(report.getId(), report.getLocation(), report.getPhotoRaincatch(), report.getCreatedDate()));
        }
        return findResult;
    }

    /**
     * 전체 신고 목록 확인 - 관리자 (승인 전)
     */
    public ArrayList<ReportFindAllRes> findAdminReport(){
        ArrayList<ReportFindAllRes> findAdminReport = new ArrayList<ReportFindAllRes>();

        ArrayList<Report> reports = reportRepository.findAllByStateOrderByCreatedDateDesc(ReportState.UNAPPROVAL);

        for(Report report: reports) {
            findAdminReport.add(new ReportFindAllRes(report.getId(), report.getLocation(), report.getPhotoRaincatch(), report.getCreatedDate()));
        }
        return findAdminReport;
    }


    /**
     * 신고 상세 보기
     */
    public ReportDetailRes getReport(Long reportId) {
        Report report = reportRepository.findById(reportId).get();
        ReportDetailRes reportDetailRes = new ReportDetailRes(reportId, report.getLatitude(), report.getLongitude(),
                report.getLocation(), report.getPhotoRaincatch(), report.getPhotoAround(), report.getDescription());
        return reportDetailRes;
    }
}
