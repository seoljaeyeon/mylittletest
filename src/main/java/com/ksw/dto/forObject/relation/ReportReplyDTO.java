package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.ReplyDTO;
import com.ksw.dto.forObject.entity.ReportDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class ReportReplyDTO implements Serializable{

	private static final long serialVersionUID = 1L;

    private UserDTO userDTO;
    private ReplyDTO replyDTO;
    
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

	public ReportDTO getReportDTO() {
		return reportDTO;
	}

	public void setReportDTO(ReportDTO reportDTO) {
		this.reportDTO = reportDTO;
	}

	private ReportDTO reportDTO;

    // 기본 생성자
    public ReportReplyDTO() {}
}
