package com.project.doday.repository;

import com.project.doday.domain.Report;
import com.project.doday.domain.ReportState;
import com.project.doday.dto.ReportFindAllRes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    ArrayList<Report> findAllByStateOrderByCreatedDateDesc(ReportState state);

    List<Report> findAllByMemberIdOrderByCreatedDateDesc(Long memberId);

}
