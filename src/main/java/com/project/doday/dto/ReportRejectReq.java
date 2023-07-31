package com.project.doday.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "신고 반려 모델")
public class ReportRejectReq {
    @Schema(description = "관리자 아이디" , example = "1")
    private Long adminId;

    @Schema(description = "반려 사유" , example = "청소할 필요가 없어 보입니다.")
    private String content;
}
