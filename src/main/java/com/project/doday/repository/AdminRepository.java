package com.project.doday.repository;

import com.project.doday.domain.Admin;
import com.project.doday.domain.Report;
import com.project.doday.domain.ReportState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
