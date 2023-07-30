package com.project.doday.service;

import com.project.doday.domain.Report;
import com.project.doday.domain.Solution;
import com.project.doday.dto.MyReportRes;
import com.project.doday.dto.SolutionListRes;
import com.project.doday.repository.ReportRepository;
import com.project.doday.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final SolutionRepository solutionRepository;
    private final ReportRepository reportRepository;

    /**
     * 나의 해결 목록 보기
     */
    @Transactional(readOnly = true)
    public List<SolutionListRes> getMySolution(Long memberId) {
        List<Solution> solutions = solutionRepository.findAll();
        List<SolutionListRes> mySolution = new ArrayList<>();
        for(Solution solution : solutions) {
            // TODO 반려 사유
            if(solution.getMember().getId() == memberId) {
                SolutionListRes solutionRes = new SolutionListRes(
                        solution.getId(), solution.getReportDate(), solution.getLocation(),
                        solution.getPhoto(), "", solution.getState());

                mySolution.add(solutionRes);
            }
        }

        return mySolution;
    }

    /**
     * 나의 신고 목록 보기
     */
    @Transactional(readOnly = true)
    public ArrayList<MyReportRes> getMyReport(Long memberId){
        List<Report> reports = reportRepository.findAll();
        ArrayList<MyReportRes> myReport = new ArrayList<>();

        for(Report report : reports) {
            if(Objects.equals(report.getMember().getId(), memberId)){
                MyReportRes myReportRes = new MyReportRes(
                        report.getId(), report.getLocation(), report.getPhotoRaincatch(), report.getCreatedDate(),
                        report.getState(), "");
                myReport.add(myReportRes);
            }
        }
        return myReport;

    }

}