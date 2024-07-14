package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class ReplyUserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private UserDTO userDTO;
    private ReplyDTO replyDTO;

    // 기본 생성자
    public ReplyUserDTO() {}

    // 기본 생성자
    public ReplyUserDTO(ReplyDTO replyDTO, UserDTO userDTO) {
    	super();
    	this.replyDTO = replyDTO;
    	this.userDTO = userDTO;
    }

    
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public ReplyDTO getReplyDTO() {
		return replyDTO;
	}

	public void setReplyDTO(ReplyDTO replyDTO) {
		this.replyDTO = replyDTO;
	}

}
