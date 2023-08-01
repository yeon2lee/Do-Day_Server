package com.project.doday.controller;

import com.project.doday.dto.RewardConvertReq;
import com.project.doday.dto.RewardRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.project.doday.domain.Reward;
import com.project.doday.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "리워드 API", description = "리워드 관련 API")
public class RewardController {
    private final RewardService rewardService;

    /**
     * 리워드 내역 확인하기
     */
    @Operation(summary = "나의 리워드 내역 보기 API", description = "나의 리워드 내역을 볼 때 사용되는 API입니다.")
    @Parameter(name = "memberId", description = "내 고유 id값")
    @GetMapping("/reward/{memberId}")
    public List<RewardRes> getRewardList(@PathVariable Long memberId) {
        List<RewardRes> rewards = rewardService.getMyReward(memberId);
        return rewards;
    }

    /**
     * 리워드 전환하기
     */
    @Operation(summary = "리워드 전환하기 API", description = "리워드를 전환할 때 사용되는 API입니다.")
    @Parameter(name = "memberId", description = "내 고유 id값")
    @PutMapping("/reward/convert/{memberId}")
    public Long convertReward(@PathVariable Long memberId, @RequestBody RewardConvertReq rewardConvertReq) {
        Long convertReward = rewardService.convertReward(memberId, rewardConvertReq);
        return convertReward;
    }
}