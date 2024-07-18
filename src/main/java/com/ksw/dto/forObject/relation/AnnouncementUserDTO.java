package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.AnnouncementDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class AnnouncementUserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private AnnouncementDTO announcementDTO;
    private UserDTO userDTO;

    // 기본 생성자
    public AnnouncementUserDTO() {}

	public AnnouncementDTO getAnnouncementDTO() {
		return announcementDTO;
	}

	public void setAnnouncementDTO(AnnouncementDTO announcementDTO) {
		this.announcementDTO = announcementDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
}
