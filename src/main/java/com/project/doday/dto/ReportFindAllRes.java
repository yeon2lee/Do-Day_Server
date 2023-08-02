package com.project.doday.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "신고 목록 모델")
public class ReportFindAllRes {
    @Schema(description = "신고 아이디" , example = "1")
    private Long reportId;

    @Schema(description = "신고 위치" , example = "서울시 광진구 화양동 7")
    private String location;

    @Schema(description = "빗물받이 사진(대표 이미지)" , example = "")
    private String photoRaincatch;

    @Schema(description = "신고 날짜" , example = "2023-07-31T09:03:23.924Z")
    private LocalDateTime createdDate;
}
