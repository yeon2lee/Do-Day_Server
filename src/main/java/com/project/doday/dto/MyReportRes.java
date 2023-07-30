package com.project.doday.dto;

import com.project.doday.domain.ReportState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MyReportRes {

    private Long reportId;
    private String location;
    private String photoRaincatch;
    private LocalDateTime createDate;
    private ReportState state;
    private String content; // 반려 사유

}
