package com.ksw.object.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "favorite")
public class Favorite implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteNo;

	@Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(nullable = true)
    private Integer favoriteType; // -1 이면 덜보기 1이면 좋아요 / 북마크
	
	// constructor
	public Favorite() {
		super();
	}

	// 엔티티가 처음 저장되기 전에 실행
    @PrePersist
    protected void onCreate() {
    	createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
	
	public Integer getFavoriteNo() {
		return favoriteNo;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public Integer getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(Integer favoriteType) {
		this.favoriteType = favoriteType;
	}

	public void setFavoriteNo(Integer favoriteNo) {
		this.favoriteNo = favoriteNo;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
