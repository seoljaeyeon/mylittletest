package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.ReportNoteDTO;
import com.ksw.object.relation.ReportNote;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.ReportService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.ReportNoteVO;

@Service
public class ReportNoteService {

	@Autowired
	private NoteService noteService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private UserService userService;
	
    // Entity -> DTO 변환 메소드
    public ReportNoteDTO convertToDTO(ReportNote reportNoteEntity) {
    	ReportNoteDTO dto = new ReportNoteDTO();
    	dto.setNoteDTO(noteService.convertToDTO(reportNoteEntity.getNote()));
    	dto.setReportDTO(reportService.convertToDTO(reportNoteEntity.getReport()));
    	dto.setUserDTO(userService.convertToDTO(reportNoteEntity.getUser()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public ReportNoteVO convertToVO(ReportNoteDTO reportNoteDTO) {
        return new ReportNoteVO.Builder()
                .userVO(userService.convertToVO(reportNoteDTO.getUserDTO()))
                .noteVO(noteService.convertToVO(reportNoteDTO.getNoteDTO()))
                .reportVO(reportService.convertToVO(reportNoteDTO.getReportDTO()))
                .build();
    }
}
