package com.project.doday.dto;

import com.project.doday.domain.SolutionState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Schema(description = "리워드 내역 모델")
public class RewardRes {
    @Schema(description = "리워드 아이디" , example = "1")
    private Long rewardId;

    @Schema(description = "적립 날짜" , example = "2023-07-31T09:03:23.924Z")
    private LocalDateTime date;

    @Schema(description = "적립 금액" , example = "100")
    private Long price;

    @Schema(description = "신고하기/해결하기" , example = "신고하기")
    private String type;

    @Schema(description = "신고 위치" , example = "서울시 광진구 화양동 7")
    private String location;
}
