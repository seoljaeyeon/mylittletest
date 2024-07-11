package com.ksw.object.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "view")
public class View {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer viewNo;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Getters and Setters
    public Integer getViewNo() {
        return viewNo;
    }

    public void setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
