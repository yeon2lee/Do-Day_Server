package com.project.doday.dto;

import com.project.doday.domain.Solution;
import com.project.doday.domain.SolutionState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SolutionListRes {
    private Long solutionId;

    private LocalDateTime reportDate;

    private String location;
    private String photo;
    private String content;

    private SolutionState state;

    public Solution toEntity() {
        return Solution.builder()
                .reportDate(reportDate)
                .location(location)
                .photo(photo)
                .state(state)
                .build();
    }
}
