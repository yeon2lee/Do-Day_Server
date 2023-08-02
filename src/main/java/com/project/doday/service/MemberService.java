package com.project.doday.service;

import com.project.doday.domain.*;
import com.project.doday.dto.MyReportRes;
import com.project.doday.dto.MyRewardRes;
import com.project.doday.dto.SolutionListRes;
import com.project.doday.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final SolutionRepository solutionRepository;
    private final SolutionRejectRepository solutionRejectRepository;
    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    private final ReportRejectRepository reportRejectRepository;

    /**
     * 나의 해결 목록 보기
     */
    @Transactional(readOnly = true)
    public List<SolutionListRes> getMySolution(Long memberId) {
        List<Solution> solutions = solutionRepository.findAll();
        List<SolutionListRes> mySolution = new ArrayList<>();
        for(Solution solution : solutions) {
            // 반려 내역 불러오기
            Optional<SolutionReject> solutionReject = solutionRejectRepository.findById(solution.getId());
            String content;
            if (solutionReject.isPresent()) {
                content = solutionReject.get().getContent();
            } else {
                content = null; // 반려가 아니라 승인일 경우에는 ""로 보냄
            }

            if(solution.getMember().getId() == memberId) {
                SolutionListRes solutionRes = new SolutionListRes(
                        solution.getId(), solution.getCreatedDate(), solution.getLocation(),
                        solution.getPhoto(), content, solution.getState());

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
            Optional<ReportReject> reportsReject = reportRejectRepository.findById(report.getId());
            String content;
            content = reportsReject.map(ReportReject::getContent).orElse(null);

            if(Objects.equals(report.getMember().getId(), memberId)){
                MyReportRes myReportRes = new MyReportRes(
                        report.getId(), report.getLocation(), report.getPhotoRaincatch(), report.getCreatedDate(),
                        report.getState(), content);
                myReport.add(myReportRes);
            }
        }
        return myReport;

    }

    /**
     * 나의 리워드 금액 보기
     */
    public MyRewardRes getMyReward(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        MyRewardRes myRewardRes = new MyRewardRes(member.getNowReward(), member.getTotalReward());
        return myRewardRes;
    }
}