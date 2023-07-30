package com.project.doday.dto;

import com.project.doday.domain.Solution;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SolutionReq {

    private Double latitude;
    private Double longitude;
    private String location;
    private String photo;
    private String falseReport;

    public Solution toEntity() {
        return Solution.builder()
                .latitude(latitude)
                .longitude(longitude)
                .location(location)
                .photo(photo)
                .falseReport(falseReport)
                .build();
    }

}
