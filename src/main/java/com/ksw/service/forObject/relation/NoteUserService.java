package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.NoteUserDTO;
import com.ksw.object.relation.NoteUser;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.NoteUserVO;

@Service
public class NoteUserService {

	@Autowired
	private NoteService noteService;
	@Autowired
	private UserService userService;
	
    // Entity -> DTO 변환 메소드
    public NoteUserDTO convertToDTO(NoteUser noteUserEntity) {
    	NoteUserDTO dto = new NoteUserDTO();
    	dto.setNoteDTO(noteService.convertToDTO(noteUserEntity.getNote()));
    	dto.setUserDTO(userService.convertToDTO(noteUserEntity.getUser()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public NoteUserVO convertToVO(NoteUserDTO noteUserDTO) {
        return new NoteUserVO.Builder()
                .userVO(userService.convertToVO(noteUserDTO.getUserDTO()))
                .noteVO(noteService.convertToVO(noteUserDTO.getNoteDTO()))
                .build();
    }
}
