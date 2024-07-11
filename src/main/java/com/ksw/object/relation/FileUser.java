package com.ksw.object.relation;

import com.ksw.object.entity.File;
import com.ksw.object.entity.User;

public class FileUser {

    private User user;
    private File file;

    // 기본 생성자
    public FileUser() {}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
