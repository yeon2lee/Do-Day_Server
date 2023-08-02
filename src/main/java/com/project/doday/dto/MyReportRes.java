package com.project.doday.dto;

import com.project.doday.domain.ReportState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "내 신고 모델")
public class MyReportRes {

    @Schema(description = "신고 아이디" , example = "1")
    private Long reportId;

    @Schema(description = "신고 위치" , example = "서울시 광진구 화양동 7")
    private String location;

    @Schema(description = "신고 대표사진" , example = "1")
    private String photoRaincatch;

    @Schema(description = "신고 날짜" , example = "2023,\n" +
            "            7,\n" +
            "            31,\n" +
            "            1,\n" +
            "            21,\n" +
            "            10,\n" +
            "            496918000")
    private LocalDateTime createdDate;

    @Schema(description = "신고 상태" , example = "UNRESOLVED")
    private ReportState state;

    @Schema(description = "반려 사유" , example = "청소할 필요가 없다고 판단됨")
    private String content; // 반려 사유

}
