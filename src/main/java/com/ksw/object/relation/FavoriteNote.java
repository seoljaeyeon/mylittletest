package com.ksw.object.relation;

import java.io.Serializable;

import com.ksw.object.entity.Favorite;
import com.ksw.object.entity.Note;
import com.ksw.object.entity.User;

public class FavoriteNote implements Serializable
{
	private static final long serialVersionUID = 1L;
	
    private User user;
    private Note note;
    private Favorite favorite;

    // 기본 생성자
    public FavoriteNote() {}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}
}
