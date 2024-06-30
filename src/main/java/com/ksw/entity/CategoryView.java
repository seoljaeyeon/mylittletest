package com.ksw.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "category_view")
public class CategoryView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryViewNo;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

	public Integer getCategoryViewNo() {
		return categoryViewNo;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCategoryViewNo(Integer categoryViewNo) {
		this.categoryViewNo = categoryViewNo;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
