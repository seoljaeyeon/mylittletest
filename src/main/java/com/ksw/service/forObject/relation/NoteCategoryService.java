package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.NoteCategoryDTO;
import com.ksw.object.relation.NoteCategory;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.vo.forObject.relation.NoteCategoryVO;

@Service
public class NoteCategoryService {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private NoteService noteService;
	
    // Entity -> DTO 변환 메소드
    public NoteCategoryDTO convertToDTO(NoteCategory noteCategoryEntity) {
    	NoteCategoryDTO dto = new NoteCategoryDTO();
    	
    	dto.setCategoryDTO(categoryService.convertToDTO(noteCategoryEntity.getCategory()));
    	dto.setNoteDTO(noteService.convertToDTO(noteCategoryEntity.getNote()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public NoteCategoryVO convertToVO(NoteCategoryDTO noteCategoryDTO) {
        return new NoteCategoryVO.Builder()
                .categoryVO(categoryService.convertToVO(noteCategoryDTO.getCategoryDTO()))
                .noteVO(noteService.convertToVO(noteCategoryDTO.getNoteDTO()))
                .build();
    }
}
