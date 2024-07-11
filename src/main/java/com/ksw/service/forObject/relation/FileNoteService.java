package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.FileNoteDTO;
import com.ksw.object.relation.FileNote;
import com.ksw.service.forObject.entity.FileService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.vo.forObject.relation.FileNoteVO;

@Service
public class FileNoteService {
	
	@Autowired
	private FileService fileService;
	@Autowired 
	private NoteService noteService;
	
    // Entity -> DTO 변환 메소드
    public FileNoteDTO convertToDTO(FileNote fileNoteEntity) {
    	FileNoteDTO dto = new FileNoteDTO();
    	
    	dto.setFileDTO(fileService.convertToDTO(fileNoteEntity.getFile()));
    	dto.setNoteDTO(noteService.convertToDTO(fileNoteEntity.getNote()));
    	
        return dto;
    }

    // DTO -> VO 변환 메소드
    public FileNoteVO convertToVO(FileNoteDTO fileNoteDTO) {
        return new FileNoteVO.Builder()
                .noteVO(noteService.convertToVO(fileNoteDTO.getNoteDTO()))
                .fileVO(fileService.convertToVO(fileNoteDTO.getFileDTO()))
                .build();
    }
}
