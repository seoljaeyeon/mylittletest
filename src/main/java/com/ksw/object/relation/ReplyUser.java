package com.ksw.object.relation;

import com.ksw.object.entity.Reply;
import com.ksw.object.entity.User;

public class ReplyUser {

    private User user;
    private Reply reply;

    // 기본 생성자
    public ReplyUser() {}

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

}
