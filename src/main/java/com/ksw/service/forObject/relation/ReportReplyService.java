package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.ReportReplyDTO;
import com.ksw.object.relation.ReportReply;
import com.ksw.service.forObject.entity.ReplyService;
import com.ksw.service.forObject.entity.ReportService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.ReportReplyVO;

@Service
public class ReportReplyService {

	@Autowired
	private ReplyService replyService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private UserService userService;
	
    // Entity -> DTO 변환 메소드
    public ReportReplyDTO convertToDTO(ReportReply reportReplyEntity) {
    	ReportReplyDTO dto = new ReportReplyDTO();
    	dto.setReplyDTO(replyService.convertToDTO(reportReplyEntity.getReply()));
    	dto.setReportDTO(reportService.convertToDTO(reportReplyEntity.getReport()));
    	dto.setUserDTO(userService.convertToDTO(reportReplyEntity.getUser()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public ReportReplyVO convertToVO(ReportReplyDTO reportReplyDTO) {
        return new ReportReplyVO.Builder()
                .userVO(userService.convertToVO(reportReplyDTO.getUserDTO()))
                .replyVO(replyService.convertToVO(reportReplyDTO.getReplyDTO()))
                .reportVO(reportService.convertToVO(reportReplyDTO.getReportDTO()))
                .build();
    }
}
