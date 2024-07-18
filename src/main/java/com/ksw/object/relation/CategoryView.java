package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Category;
import com.ksw.object.entity.User;
import com.ksw.object.entity.View;

public class CategoryView implements Serializable
{
    
	private static final long serialVersionUID = 1L;

	
    private Category category;
    private View view;
    private User user;
    
	public Category getCategory() {
		return category;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public View getView() {
		return view;
	}
	public void setView(View view) {
		this.view = view;
	}

}
