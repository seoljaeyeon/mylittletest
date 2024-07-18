package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Category;
import com.ksw.object.entity.Favorite;
import com.ksw.object.entity.User;

public class FavoriteCategory implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private User user;
    private Category category;
    private Favorite favorite;

    // 기본 생성자
    public FavoriteCategory() {}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}

    
}
