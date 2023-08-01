package com.project.doday.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "리워드 전환 모델")
public class RewardConvertReq {
    @Schema(description = "전환 금액" , example = "1000")
    private Long amount;
}

