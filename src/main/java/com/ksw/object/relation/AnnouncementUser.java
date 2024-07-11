package com.ksw.object.relation;

import com.ksw.object.entity.Announcement;
import com.ksw.object.entity.User;

public class AnnouncementUser {

    private Announcement announcement;
    private User user;

    // 기본 생성자
    public AnnouncementUser() {}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
