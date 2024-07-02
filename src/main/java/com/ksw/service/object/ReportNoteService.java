package com.ksw.service.object;

import com.ksw.dto.forObject.ReportNoteDTO;
import com.ksw.object.entity.mybatis.ReportNote;
import com.ksw.object.vo.ReportNoteVO;
import org.springframework.stereotype.Service;

@Service
public class ReportNoteService {

    // Entity -> DTO 변환 메소드
    public ReportNoteDTO convertToDTO(ReportNote reportNoteEntity) {
        return new ReportNoteDTO.Builder()
                .userNo(reportNoteEntity.getUserNo())
                .noteNo(reportNoteEntity.getNoteNo())
                .reportNo(reportNoteEntity.getReportNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public ReportNoteVO convertToVO(ReportNoteDTO reportNoteDTO) {
        return new ReportNoteVO.Builder()
                .userNo(reportNoteDTO.getUserNo())
                .noteNo(reportNoteDTO.getNoteNo())
                .reportNo(reportNoteDTO.getReportNo())
                .build();
    }
}
