package com.ksw.object.relation;

import com.ksw.object.entity.Announcement;
import com.ksw.object.entity.File;

public class FileAnnouncement {

    private Announcement announcement;
    private File file;

    // 기본 생성자
    public FileAnnouncement() {}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
