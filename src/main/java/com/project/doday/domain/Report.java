package com.project.doday.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;

    private String location;

    private String photoRaincatch;

    @Nullable
    private String photoAround;

    private String description;

    @Enumerated(EnumType.STRING)
    private ReportState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;


    @Builder
    public Report(Member member, Double latitude, Double longitude, String location, String photoRaincatch, String photoAround, String description, ReportState state) {
        this.member = member;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.photoRaincatch = photoRaincatch;
        this.photoAround = photoAround;
        this.description = description;
        this.state = state;
    }

}
