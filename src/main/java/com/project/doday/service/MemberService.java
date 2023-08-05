package com.project.doday.service;

import com.project.doday.domain.*;
import com.project.doday.dto.*;
import com.project.doday.exception.BusinessException;
import com.project.doday.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    /**
     * 나의 해결 목록 보기
     */
    @Transactional(readOnly = true)
    public List<SolutionListRes> getMySolution(Long memberId) {
        List<Solution> solutions = solutionRepository.findAllByOrderByCreatedDateDesc();
        List<SolutionListRes> mySolution = new ArrayList<>();
        for(Solution solution : solutions) {
            // 반려 내역 불러오기
            Optional<SolutionReject> solutionReject = solutionRejectRepository.findBySolutionId(solution.getId());
            String content;
            if (solutionReject.isPresent()) {
                content = solutionReject.get().getContent();
            } else {
                content = ""; // 반려가 아니라 승인일 경우에는 ""로 보냄
            }

            if(solution.getMember().getId() == memberId) {
                SolutionListRes solutionRes = new SolutionListRes(
                        solution.getId(), solution.getCreatedDate(),
                        solution.getReportDate(), solution.getLocation(),
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
        List<Report> reports = reportRepository.findAllByMemberIdOrderByCreatedDateDesc(memberId);
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


    /**
     * 회원가입
     */
    public Long signInMember(MemberSignReq memberSignReq) {
        // 중복유저
        if(memberRepository.existsMemberByUserId(memberSignReq.getUserId())){
            return (long) -1;
        }

        String pwd = passwordEncoder.encode(memberSignReq.getPassword());
        Member signInUser = Member.builder()
                .userId(memberSignReq.getUserId())
                .password(pwd)
                .nowReward(0L)
                .totalReward(0L)
                .build();

        memberRepository.save(signInUser);
        return signInUser.getId();
    }

    /**
     * 로그인
     */
    public MemberLoginRes login(MemberLoginReq memberLoginReq){

        Member user = memberRepository.findAllByUserId(memberLoginReq.getUserId());

        if(user==null)
            throw new BusinessException(ErrorMessage.USER_NOT_FOUND);

        if(!passwordEncoder.matches(memberLoginReq.getPassword(), user.getPassword())){
            throw new BusinessException(ErrorMessage.WRONG_PASSWORD);
        }

        return new MemberLoginRes(user.getId());
    }


}