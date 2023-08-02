package com.project.doday.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Solution extends BaseEntity {
    @Id @GeneratedValue
    @JsonProperty(value="solutionId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    @JsonIgnore
    private Report report;

    private Double latitude;
    private Double longitude;
    private String location;
    private String photo;
    private String falseReport;

    @Enumerated(EnumType.STRING)
    private SolutionState state;

    private LocalDateTime reportDate;

}
