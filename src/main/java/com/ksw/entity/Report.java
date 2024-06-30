package com.ksw.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportNo;

    @Column(nullable = false)
    private Integer reportType;

    @Column(nullable = false, length = 255)
    private String reportNote;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer solvedType = 0;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column
    private Timestamp solvedAt;

	public Integer getReportNo() {
		return reportNo;
	}

	public Integer getReportType() {
		return reportType;
	}

	public String getReportNote() {
		return reportNote;
	}

	public Integer getSolvedType() {
		return solvedType;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public Timestamp getSolvedAt() {
		return solvedAt;
	}

	public void setReportNo(Integer reportNo) {
		this.reportNo = reportNo;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public void setReportNote(String reportNote) {
		this.reportNote = reportNote;
	}

	public void setSolvedType(Integer solvedType) {
		this.solvedType = solvedType;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setSolvedAt(Timestamp solvedAt) {
		this.solvedAt = solvedAt;
	}
}
