package com.ksw.service.forObject.relation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public Integer getRandomNobyCategoryTitle(String categoryTitle, Integer userNo, Integer menuType) {
		
		// menuType - 1 :: mytest, 내 문제 풀기
		// menuType - 2 :: reviewmytest, 맞춘 문제 복
		// menuType - 3 :: correctmytest, 틀린 문제 복습
		// menuType - 4 :: todayquestions, 오늘 본 문제 복습
		Integer result = 0;
		if (categoryTitle == null || categoryTitle.equals("")) {
			System.out.println("categoryTitle is null. Empty List<NoteDTO> returned");
			return result;
		}

	    Integer categoryNo = categoryService.getCategoryNoByTitle(categoryTitle);
	    System.out.println("categoryNo: "+categoryNo);
	    Integer previousNoteNo = noteViewservice.getPreviousNoteNo(categoryNo, userNo);
	    System.out.println("previeousNoteNo: "+previousNoteNo);
    	
    	int attempts = 0;
    	int maxAttempts = 10;
	    
	    if (menuType == 1) {
	    	result = noteCategoryMapper.getRandomNoteNo(categoryTitle, userNo);
	    	if (result == null) {
	    		result = 0;
	    		return result;
	    	}
	    	System.out.println("실행 메뉴 타입: "+menuType+" , result = " + result);
	    	while (result.equals(previousNoteNo) && attempts < maxAttempts) {
	    		result = noteCategoryMapper.getRandomNoteNo(categoryTitle, userNo);
	    		attempts++;
	    	}
	    } else if(menuType == 2) {
	    	result = noteCategoryMapper.getReviewRandomNoteNo(categoryTitle, userNo);
	    	if (result == null) {
	    		result = 0;
	    		return result;
	    	}
	    	System.out.println("실행 메뉴 타입: "+menuType+" , result = " + result);
	    	while (result.equals(previousNoteNo) && attempts < maxAttempts) {
	    		result = noteCategoryMapper.getReviewRandomNoteNo(categoryTitle, userNo);
	    		attempts++;
	    	}
	    } else if(menuType == 3) {
	    	result = noteCategoryMapper.getCorrectRandomNoteNo(categoryTitle, userNo);
	    	if (result == null) {
	    		result = 0;
	    		return result;
	    	}
	    	System.out.println("실행 메뉴 타입: "+menuType+" , result = " + result);
	    	while (result.equals(previousNoteNo) && attempts < maxAttempts) {
	    		result = noteCategoryMapper.getCorrectRandomNoteNo(categoryTitle, userNo);
	    		attempts++;
	    	}
	    } else if(menuType == 4) {
	    	result = noteCategoryMapper.getTodayQuestionRandomNoteNo(categoryTitle, userNo);
	    	if (result == null) {
	    		result = 0;
	    		return result;
	    	}
	    	System.out.println("실행 메뉴 타입: "+menuType+" , result = " + result);
	    	while (result.equals(previousNoteNo) && attempts < maxAttempts) {
	    		result = noteCategoryMapper.getTodayQuestionRandomNoteNo(categoryTitle, userNo);
	    		attempts++;
	    	}
	    } else if(menuType == 5) {
	    	result = noteCategoryMapper.getBookmarkQuestionRandomNoteNo(categoryTitle, userNo);
	    	if (result == null) {
	    		result = 0;
	    		return result;
	    	}
	    	System.out.println("실행 메뉴 타입: "+menuType+" , result = " + result);
	    	while (result.equals(previousNoteNo) && attempts < maxAttempts) {
	    		result = noteCategoryMapper.getBookmarkQuestionRandomNoteNo(categoryTitle, userNo);
	    		attempts++;
	    	}
	    } else if(menuType == 0) {
	    	result = noteCategoryMapper.getAllQuestionRandomNoteNo(categoryTitle, userNo);
	    	if (result == null) {
	    		result = 0;
	    		return result;
	    	}
	    	System.out.println("실행 메뉴 타입: "+menuType+" , result = " + result);
	    	while (result.equals(previousNoteNo) && attempts < maxAttempts) {
	    		result = noteCategoryMapper.getAllQuestionRandomNoteNo(categoryTitle, userNo);
	    		attempts++;
	    	}
	    }
	    
	    if (attempts == 10 && result.equals(previousNoteNo)) {
	    	return result;
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
