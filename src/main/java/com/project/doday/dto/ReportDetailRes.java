package com.project.doday.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ReportDetailRes {
    private Long reportId;

    private Double latitude;

    private Double longitude;

    private String location;

    private String photoRaincatch;

    private String photoAround;

    private String description;
}
