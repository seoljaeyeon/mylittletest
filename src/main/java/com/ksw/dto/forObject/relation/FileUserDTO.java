package com.ksw.dto.forObject.relation;

import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class FileUserDTO {

    private UserDTO userDTO;
    private FileDTO fileDTO;

    // 기본 생성자
    public FileUserDTO() {}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public FileDTO getFileDTO() {
		return fileDTO;
	}

	public void setFileDTO(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}
}
