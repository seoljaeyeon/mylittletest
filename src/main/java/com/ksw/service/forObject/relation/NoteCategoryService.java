package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.NoteCategoryMapper;
import com.ksw.dto.forObject.relation.NoteCategoryDTO;
import com.ksw.object.entity.Category;
import com.ksw.object.entity.Note;
import com.ksw.object.relation.NoteCategory;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.relation.NoteCategoryVO;

@Service
public class NoteCategoryService {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private NoteCategoryMapper noteCategoryMapper;
	
	// noteNo로 categoryVO 반환 메소드
	public CategoryVO getCategoryVObynoteNo(Integer noteNO) {
		if (noteNO == null) {
			System.out.println("Getting CategoryVO failed. noteNo is null. Empty CategoryVO returned.");
			return new CategoryVO.Builder().build();
		}
		return categoryService.convertToVO(categoryService.convertToDTO(noteCategoryMapper.getNoteCategorybynoteNo(noteNO).getCategory()));
	}
	
    // Entity -> DTO 변환 메소드
    public NoteCategoryDTO convertToDTO(NoteCategory noteCategoryEntity) {
    	NoteCategoryDTO dto = new NoteCategoryDTO();
    	if (noteCategoryEntity == null) {
    		System.out.println("NoteCategory to NoteCategoryDTO failed. Empty NoteCategoryDTO created. NoteCategory is null");    		
    		return dto;
    	}
    	dto.setCategoryDTO(categoryService.convertToDTO(noteCategoryEntity.getCategory()));
    	dto.setNoteDTO(noteService.convertToDTO(noteCategoryEntity.getNote()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public NoteCategoryVO convertToVO(NoteCategoryDTO noteCategoryDTO) {
    	if (noteCategoryDTO == null) {
    		System.out.println("NoteCategoryDTO to NoteCategoryVO failed. Empty NoteCategoryVO created. NoteCategoryDTO is null");    		
    		return new NoteCategoryVO.Builder().build();
    	}
        return new NoteCategoryVO.Builder()
                .categoryVO(categoryService.convertToVO(noteCategoryDTO.getCategoryDTO()))
                .noteVO(noteService.convertToVO(noteCategoryDTO.getNoteDTO()))
                .build();
    }
    
    // DTO -> Entity 변환 메소드
    public NoteCategory convertToEntity(NoteCategoryDTO noteCategoryDTO) {
        NoteCategory noteCategoryEntity = new NoteCategory();
        if(noteCategoryDTO == null) {
    		System.out.println("NoteCategoryDTO to NoteCategory failed. Empty NoteCategory created. NoteCategoryDTO is null");    		
        	return noteCategoryEntity;
        }
        Category categoryEntity = categoryService.convertToEntity(noteCategoryDTO.getCategoryDTO());
        Note noteEntity = noteService.convertToEntity(noteCategoryDTO.getNoteDTO());

        noteCategoryEntity.setCategory(categoryEntity);
        noteCategoryEntity.setNote(noteEntity);
        return noteCategoryEntity;
    }
}
