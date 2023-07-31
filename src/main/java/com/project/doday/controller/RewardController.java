package com.project.doday.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.project.doday.domain.Reward;
import com.project.doday.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity getSolutionList(@PathVariable Long memberId) {
        List<Reward> rewards = rewardService.getMyReward(memberId);
        return ResponseEntity.ok(rewards);
    }

    /**
     * 리워드 전환하기
     */
    @Operation(summary = "리워드 전환하기 API", description = "리워드를 전환할 때 사용되는 API입니다.")
    @Parameter(name = "memberId", description = "내 고유 id값")
    @PutMapping("/reward/convert/{memberId}")
    public Long convertReward(@PathVariable Long memberId, Long amount) {
        Long convertReward = rewardService.convertReward(memberId, amount);
        return convertReward;
    }
}