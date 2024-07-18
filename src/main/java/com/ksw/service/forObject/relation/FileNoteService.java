package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.FileNoteDTO;
import com.ksw.object.entity.File;
import com.ksw.object.entity.Note;
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
    	if(fileNoteEntity == null) {
    		System.out.println("FileNote to FileNoteDTO failed. Empty FileNoteDTO created. FileNote is null");    		
    		return dto;
    	}
    	dto.setFileDTO(fileService.convertToDTO(fileNoteEntity.getFile()));
    	dto.setNoteDTO(noteService.convertToDTO(fileNoteEntity.getNote()));
    	
        return dto;
    }

    public FileNote convertToEntity(FileNoteDTO fileNoteDTO) {
        FileNote fileNoteEntity = new FileNote();
        if (fileNoteDTO == null) {
        	System.out.println("FileNoteDTO to FileNote failed. Empty FileNote created. FileNoteDTO is null");    		
        	return fileNoteEntity;
        }

        File fileEntity = fileService.convertToEntity(fileNoteDTO.getFileDTO());
        Note noteEntity = noteService.convertToEntity(fileNoteDTO.getNoteDTO());

        fileNoteEntity.setFile(fileEntity);
        fileNoteEntity.setNote(noteEntity);

        return fileNoteEntity;
    }
    
    // DTO -> VO 변환 메소드
    public FileNoteVO convertToVO(FileNoteDTO fileNoteDTO) {
		if (fileNoteDTO == null) {
			System.out.println("FileNoteDTO to FileNoteVO failed. Empty FileNoteVO created. FileNoteDTO is null");    		
			return new FileNoteVO.Builder().build();
		}
        return new FileNoteVO.Builder()
                .noteVO(noteService.convertToVO(fileNoteDTO.getNoteDTO()))
                .fileVO(fileService.convertToVO(fileNoteDTO.getFileDTO()))
                .build();
    }
}
