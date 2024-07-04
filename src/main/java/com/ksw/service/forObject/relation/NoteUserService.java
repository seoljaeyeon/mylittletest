package com.ksw.service.forObject.relation;

import com.ksw.dto.forObject.relation.NoteUserDTO;
import com.ksw.object.relation.NoteUser;
import com.ksw.vo.forObject.relation.NoteUserVO;

import org.springframework.stereotype.Service;

@Service
public class NoteUserService {

    // Entity -> DTO 변환 메소드
    public NoteUserDTO convertToDTO(NoteUser noteUserEntity) {
        return new NoteUserDTO.Builder()
                .userNo(noteUserEntity.getUserNo())
                .noteNo(noteUserEntity.getNoteNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public NoteUserVO convertToVO(NoteUserDTO noteUserDTO) {
        return new NoteUserVO.Builder()
                .userNo(noteUserDTO.getUserNo())
                .noteNo(noteUserDTO.getNoteNo())
                .build();
    }
}
