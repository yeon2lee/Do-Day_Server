package com.project.doday.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportReq {
    private Long memberId;
    private Double latitude;
    private Double longitude;
    private String location;
    private String photoRaincatch;
    private String photoAround;
    private String description;
}
