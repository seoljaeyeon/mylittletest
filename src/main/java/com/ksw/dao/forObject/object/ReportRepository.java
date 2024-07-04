package com.ksw.dao.forObject.object;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
