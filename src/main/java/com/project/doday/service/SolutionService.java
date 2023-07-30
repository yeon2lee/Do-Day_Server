package com.project.doday.service;

import com.project.doday.domain.Member;
import com.project.doday.domain.Report;
import com.project.doday.domain.Solution;
import com.project.doday.domain.SolutionState;
import com.project.doday.dto.SolutionDetailRes;
import com.project.doday.dto.SolutionListRes;
import com.project.doday.dto.SolutionReq;
import com.project.doday.repository.MemberRepository;
import com.project.doday.repository.ReportRepository;
import com.project.doday.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SolutionService {
    private final SolutionRepository solutionRepository;
    private final MemberRepository memberRepository;
    private final ReportRepository reportRepository;

    /**
     * 해결 신청하기
     */
    public Solution applySolution(Long reportId, Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Report report = reportRepository.findById(reportId).get();

        Solution save = solutionRepository.save(new Solution());
        save.setMember(member);
        save.setReport(report);
        // TODO solution의 reportDate를 Report를 생성한 날짜로 설정
        //save.setReportDate(report.getCreatedDate());
        save.setState(SolutionState.RESOLVING);
        // TODO: report의 state를 해결 중으로 바꾸기

        return save;
    }

    /**
     * 해결한 문제 보고하기
     */
    public Solution reportSolution(Long solutionId, Long memberId, SolutionReq solutionReq) {
        Solution solution = solutionRepository.findById(solutionId).get();

        solution.setLatitude(solutionReq.getLatitude());
        solution.setLongitude(solutionReq.getLongitude());
        solution.setLocation(solutionReq.getLocation());
        solution.setPhoto(solutionReq.getPhoto());
        solution.setFalseReport(solutionReq.getFalseReport());

        return solution;
    }

    /**
     * 전체 해결 목록 확인
     */
    @Transactional(readOnly = true)
    public List<SolutionListRes> getSolutionList() {
        List<Solution> solutions = solutionRepository.findAll();
        List<SolutionListRes> solutionListRes = new ArrayList<>();
        for(Solution solution : solutions) {
            // TODO 반려 사유

            SolutionListRes solutionRes = new SolutionListRes(
                    solution.getId(), solution.getReportDate(), solution.getLocation(),
                    solution.getPhoto(), "", solution.getState());

            solutionListRes.add(solutionRes);
        }
        return solutionListRes;
    }

    /**
     * 해결내역 상세보기
     */
    public SolutionDetailRes getSolution(Long solutionId) {
        Solution solution = solutionRepository.findById(solutionId).get();
        SolutionDetailRes solutionDetailRes = new SolutionDetailRes(solutionId, solution.getLatitude(), solution.getLongitude(),
                solution.getLocation(), solution.getPhoto(), "");
        return solutionDetailRes;
    }

}
