package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.FavoriteDTO;
import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class FavoriteReplyDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private UserDTO userDTO;
    private ReplyDTO replyDTO;
    private FavoriteDTO favoriteDTO;

    // 기본 생성자
    public FavoriteReplyDTO() {}

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

	public FavoriteDTO getFavoriteDTO() {
		return favoriteDTO;
	}

	public void setFavoriteDTO(FavoriteDTO favoriteDTO) {
		this.favoriteDTO = favoriteDTO;
	}
}
