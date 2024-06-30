package com.ksw.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteNo;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

	public Integer getFavoriteNo() {
		return favoriteNo;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setFavoriteNo(Integer favoriteNo) {
		this.favoriteNo = favoriteNo;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
