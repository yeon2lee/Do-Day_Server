package com.project.doday.dto;

import com.project.doday.domain.Solution;
import com.project.doday.domain.SolutionState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SolutionDetailRes {
    private Long solutionId;

    private Double latitude;
    private Double longitude;

    private String location;
    private String photo;
    private String content;


    public Solution toEntity() {
        return Solution.builder()
                .location(location)
                .photo(photo)
                .build();
    }
}
