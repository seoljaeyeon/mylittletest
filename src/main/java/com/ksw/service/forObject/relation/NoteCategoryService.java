package com.ksw.service.forObject.relation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.NoteCategoryMapper;
import com.ksw.dto.forObject.entity.CategoryDTO;
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
	@Autowired
	private NoteViewService noteViewservice;

	
	public List<Map<String, Object>> getNoteListByCategoryTitle(String categoryTitle) {
		List<Map<String, Object>> results = noteCategoryMapper.getNoteListByUserNo(categoryTitle);
		return results;
	}
	
	public List<Map<String, Object>> findCategoryNoteCountsByTitle(String categoryTitle){
		System.out.println(categoryTitle);
		List<Map<String, Object>> results = noteCategoryMapper.findCategoryNoteCountsByTitle(categoryTitle);
		return results;
		}
	
	public Integer getRandomNobyCategoryTitle(String categoryTitle, Integer userNo) {
		Integer result = 0;
		if (categoryTitle == null || categoryTitle.equals("")) {
			System.out.println("categoryTitle is null. Empty List<NoteDTO> returned");
			return result;
		}
		

	    Integer categoryNo = categoryService.getCategoryNoByTitle(categoryTitle);
	    Integer previousNoteNo = noteViewservice.getPreviousNoteNo(categoryNo, userNo);
	    
	    result = noteCategoryMapper.getRandomNoteNo(categoryTitle, userNo);
	    
	    int attempts = 0;
	    int maxAttempts = 10;
	    
	    while (result.equals(previousNoteNo) && attempts < maxAttempts) {
	        result = noteCategoryMapper.getRandomNoteNo(categoryTitle, userNo);
	        attempts++;
	    }
	    
	    if (result.equals(previousNoteNo)) {
	    	result = 0;
	    	return result;
	    }
	    
	    return result;
	}
	
	// 맞춘문제
	public Integer getRandomNobyCorrectCategoryTitle(String categoryTitle, Integer userNo) {
		Integer result = 0;
		if (categoryTitle == null || categoryTitle.equals("")) {
			System.out.println("categoryTitle is null. Empty List<NoteDTO> returned");
			return result;
		}
		

	    Integer categoryNo = categoryService.getCategoryNoByTitle(categoryTitle);
	    Integer previousNoteNo = noteViewservice.getPreviousNoteNo(categoryNo, userNo);
	    
	    result = noteCategoryMapper.getCorrectRandomNoteNo(categoryTitle, userNo);
	    
	    int attempts = 0;
	    int maxAttempts = 10;
	    
	    while (result.equals(previousNoteNo) && attempts < maxAttempts) {
	        result = noteCategoryMapper.getCorrectRandomNoteNo(categoryTitle, userNo);
	        attempts++;
	    }
	    
	    if (result.equals(previousNoteNo)) {
	    	result = 0;
	    	return result;
	    }
	    
	    return result;
	}
	
	
	public CategoryDTO getCategorybyNoteNo(Integer noteNo) {
		CategoryDTO dto = new CategoryDTO();
		if (noteNo == null) {
			return dto; 
		}
		dto = categoryService.convertToDTO(noteCategoryMapper.getCategorybyNoteNo(noteNo));
		return dto;
	}
	
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
