package com.ksw.service.object;

import com.ksw.dto.forObject.ReportReplyDTO;
import com.ksw.object.entity.mybatis.ReportReply;
import com.ksw.object.vo.ReportReplyVO;
import org.springframework.stereotype.Service;

@Service
public class ReportReplyService {

    // Entity -> DTO 변환 메소드
    public ReportReplyDTO convertToDTO(ReportReply reportReplyEntity) {
        return new ReportReplyDTO.Builder()
                .userNo(reportReplyEntity.getUserNo())
                .replyNo(reportReplyEntity.getReplyNo())
                .reportNo(reportReplyEntity.getReportNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public ReportReplyVO convertToVO(ReportReplyDTO reportReplyDTO) {
        return new ReportReplyVO.Builder()
                .userNo(reportReplyDTO.getUserNo())
                .replyNo(reportReplyDTO.getReplyNo())
                .reportNo(reportReplyDTO.getReportNo())
                .build();
    }
}
