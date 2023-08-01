package com.project.doday.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "내 리워드 금액 조회 모델")
public class MyRewardRes {
    @Schema(description = "현재 적립 리워드" , example = "100")
    private Long nowReward;

    @Schema(description = "총 적립 리워드" , example = "10000")
    private Long totalReward;
}
