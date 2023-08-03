package com.project.doday.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportReject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @JsonIgnore
    private Admin admin;

    private String content;

    @Builder
    public ReportReject(Long reportId, Admin admin, String content) {
        this.reportId = reportId;
        this.admin = admin;
        this.content = content;

    }
}
