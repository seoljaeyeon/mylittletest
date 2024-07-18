package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Favorite;
import com.ksw.object.entity.Reply;
import com.ksw.object.entity.User;

public class FavoriteReply implements Serializable
{
	private static final long serialVersionUID = 1L;
	
    private User user;
    private Reply reply;
    private Favorite favorite;

    // 기본 생성자
    public FavoriteReply() {}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}
}
