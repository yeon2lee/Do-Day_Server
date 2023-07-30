package com.project.doday.service;

import com.project.doday.domain.*;
import com.project.doday.dto.ReportFindAllRes;
import com.project.doday.dto.ReportReq;
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


        // TODO solution의 reportDate를 Report를 생성한 날짜로 설정
        //save.setReportDate(report.getCreatedDate());
//        save.setState(SolutionState.RESOLVING);
        // TODO: report의 state를 해결 중으로 바꾸기

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

    public ArrayList<ReportFindAllRes> findAllReport(){
        ArrayList<ReportFindAllRes> findResult = new ArrayList<ReportFindAllRes>();

        ArrayList<Report> reports = reportRepository.findAllByStateOrderByCreatedDateDesc(ReportState.UNRESOLVED);

        for(Report report: reports) {
            findResult.add(new ReportFindAllRes(report.getLocation(), report.getPhotoRaincatch(), report.getCreatedDate()));
        }
        return findResult;
    }
}
