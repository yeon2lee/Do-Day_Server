package com.project.doday.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReportFindAllRes {
    private String location;
    private String photoRaincatch;
    private LocalDateTime createDate;
}
