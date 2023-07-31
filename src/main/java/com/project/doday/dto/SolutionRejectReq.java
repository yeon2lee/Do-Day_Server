package com.project.doday.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "반려 사유 등록 모델")
public class SolutionRejectReq {

    @Schema(description = "반려사유" , example = "허위 보고로 판단되어 반려되었습니다.")
    private String content;
}
