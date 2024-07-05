package com.ksw.dao.object;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.jpa.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
