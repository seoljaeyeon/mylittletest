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
@Table(name = "view")
public class View implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer viewNo;
    
    //constructor
    public View() {
    	super();
    }
    
	// Getters and Setters
    public Integer getViewNo() {
        return viewNo;
    }

    public void setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
    }

}
