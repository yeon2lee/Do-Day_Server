package com.project.doday.dto;

import com.project.doday.domain.Solution;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SolutionReq {

    public Solution toEntity() {
        return Solution.builder()
                .build();
    }

}
