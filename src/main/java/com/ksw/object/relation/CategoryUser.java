package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Category;
import com.ksw.object.entity.User;

public class CategoryUser implements Serializable{

	private static final long serialVersionUID = 1L;

	
    private Category category;
    private User user;

    // 기본 생성자
    public CategoryUser() {}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
