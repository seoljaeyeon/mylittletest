package com.ksw.dto.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class ViewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private Integer viewNo;

    // 기본 생성자
    public ViewDTO() {}
    
    // 기본 생성자
    public ViewDTO(Integer viewNo) {
    	super();
    	this.viewNo = viewNo;
    }

    // Getter 및 Setter
    public Integer getViewNo() {
        return viewNo;
    }

    public void setViewNo(Integer viewNo) {
        this.viewNo = viewNo;
    }
}
