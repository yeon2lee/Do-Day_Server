package com.project.doday.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "신고 등록 모델")
public class ReportReq {
    @Schema(description = "사용자 아이디" , example = "1")
    private Long memberId;

    @Schema(description = "신고 위도" , example = "37.5460")
    private Double latitude;

    @Schema(description = "신고 경도" , example = "127.1039")
    private Double longitude;

    @Schema(description = "신고 위치" , example = "서울시 광진구 화양동 7")
    private String location;

    @Schema(description = "빗물받이 사진" , example = "")
    private String photoRaincatch;

    @Schema(description = "주변 환경 사진" , example = "")
    private String photoAround;

    @Schema(description = "신고 상세 설명" , example = "담배꽁초가 40개 이상 있는 것 같습니다.")
    private String description;
}
