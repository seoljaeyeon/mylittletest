package com.ksw.object.entity.jpa;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteNo;

    @Column(nullable = false)
    private Integer favoriteType = 0; // 기본값 설정
    

	@Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

	public Integer getFavoriteType() {
		return favoriteType;
	}
	
	public void setFavoriteType(Integer favoriteType) {
		this.favoriteType = favoriteType;
	}
	
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
