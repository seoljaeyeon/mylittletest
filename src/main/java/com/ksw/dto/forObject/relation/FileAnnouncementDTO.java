package com.ksw.dto.forObject.relation;

import com.ksw.dto.forObject.entity.AnnouncementDTO;
import com.ksw.dto.forObject.entity.FileDTO;

public class FileAnnouncementDTO {

    private AnnouncementDTO announcementDTO;
    private FileDTO fileDTO;

    // 기본 생성자
    public FileAnnouncementDTO() {}

	public AnnouncementDTO getAnnouncementDTO() {
		return announcementDTO;
	}

	public void setAnnouncementDTO(AnnouncementDTO announcementDTO) {
		this.announcementDTO = announcementDTO;
	}

	public FileDTO getFileDTO() {
		return fileDTO;
	}

	public void setFileDTO(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}

}
