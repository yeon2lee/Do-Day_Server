package com.project.doday.controller;

import com.project.doday.dto.MyReportRes;
import com.project.doday.dto.MyRewardRes;
import com.project.doday.dto.SolutionListRes;
import com.project.doday.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "사용자 API", description = "사용자 관련 API")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 나의 해결 목록 보기
     */
    @Operation(summary = "나의 해결 목록 보기 API", description = "나의 해결 목록들을 볼 때 사용되는 API입니다.")
    @GetMapping("/mypage/solution/{memberId}")
    @Parameter(name = "memberId", description = "내 고유 id값")
    public List<SolutionListRes> getSolutionList(@PathVariable Long memberId) {
        List<SolutionListRes> solutionList = memberService.getMySolution(memberId);
        return solutionList;
    }


    /**
     * 나의 신고 목록 보기
     */
    @Operation(summary = "나의 신고 목록 보기 API", description = "나의 신고 목록들을 볼 때 사용되는 API입니다.")
    @GetMapping("/mypage/report/{memberId}")
    @Parameter(name = "memberId", description = "내 고유 id값")
    public ArrayList<MyReportRes> getMyReportList(@PathVariable Long memberId){
        ArrayList<MyReportRes> myReportList = memberService.getMyReport(memberId);
        return myReportList;
    }

    /**
     * 나의 리워드 금액 보기
     */
    @Operation(summary = "나의 리워드 금액 보기 API", description = "나의 리워드 금액을 볼 때 사용되는 API입니다.")
    @GetMapping("/mypage/reward/{memberId}")
    @Parameter(name = "memberId", description = "내 고유 id값")
    public MyRewardRes getMyReward(@PathVariable Long memberId){
        MyRewardRes myReward = memberService.getMyReward(memberId);
        return myReward;
    }

}
