package com.ksw.service.function;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.function.CategoryDetailMapper;
import com.ksw.object.entity.Category;
import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.relation.AnswerHistoryService;
import com.ksw.service.forObject.relation.FavoriteNoteService;
import com.ksw.service.forObject.relation.NoteUserService;
import com.ksw.service.forObject.relation.NoteViewService;

@Service
public class SearchService {

	@Autowired
    private CategoryDetailMapper categoryDetailMapper;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private NoteUserService noteUserService;
	@Autowired
	private AnswerHistoryService answerHistoryService;
	@Autowired
	private NoteViewService noteViewService;
	@Autowired
	private FavoriteNoteService favoriteNoteService;
	
	public List<Map<String, Object>> searchMypage (Integer userNo, Integer limit, Integer offset, String searchInput) {
		List<Map<String, Object>> result = new ArrayList<>();
		
		result = favoriteNoteService.getFavoriteSimilarListByUserNo(userNo, limit, offset, searchInput);
		
		return result;
	}
	
	public List<List<Map<String, Object>>> search(Integer userNo, Integer menuType, Integer page, String searchInput) {
		
		List<List<Map<String,Object>>> result = new ArrayList<>();
		
		Integer limit = 10;
		Integer offset = (page-1)*limit;
		
		// 각 메뉴마다 정렬기준 달라서, 구분하기 위해 데이터 List로 한번 더 감쌈
		List<Map<String,Object>> categoryLists = new ArrayList<>();

		if(menuType == 0) {
			categoryLists = noteViewService.getSimilarCategoryListOrderedByNoteView(searchInput);
		} else if (menuType == 1) {
        	categoryLists = noteUserService.getSimilarCategoryListByUserNo(userNo, searchInput);
        } else if (menuType == 2) {
        	categoryLists = answerHistoryService.getSimilarCategoryListByUserNoAndAnswerType(userNo, 2, searchInput);
        } else if (menuType == 3) {
        	categoryLists = answerHistoryService.getSimilarCategoryListByUserNoAndAnswerType(userNo, 1, searchInput);
        } else if (menuType == 4) {
        	categoryLists = noteViewService.getTodaySimilarCategoryListByUserNo(userNo, searchInput);
        } else if (menuType == 5) {
        	categoryLists = favoriteNoteService.getSimilarCategoryListByUserNoAndFavoriteType(userNo, 2, searchInput);
        }
		
		for(Map<String, Object> categorymap : categoryLists) {
	    	Integer categoryNo = (Integer) categorymap.get("categoryNo");
	    	List<Map<String, Object>> categoryList = categoryDetailMapper.getCategoryWithNoteCount(categoryNo, limit, offset);
	    	
	    	// 리스트의 각 map들에 대해서 
	    	categoryList.stream()
	    	.map(categoryMap -> {
	    		Integer catNo = (Integer) categoryMap.get("categoryNo");
	    		
	    		// Category 객체를 직접 저장
	    		Category category = new Category();
	    		category.setCategoryNo((Integer) categoryMap.get("categoryNo"));
	    		category.setCategoryTitle((String) categoryMap.get("categoryTitle"));
	    		category.setCreatedAt((Timestamp) categoryMap.get("createdAt"));
	    		category.setIsActive((Boolean) categoryMap.get("isActive"));
	    		categoryMap.put("category", categoryService.convertToVO(categoryService.convertToDTO(category)));
	    		
	    		// 문제 수
	    		Integer noteCount = ((Number) categoryMap.get("noteCount")).intValue();
	    		categoryMap.put("noteCount", noteCount);
	    		
	    		// 정답 수/문제 수
	    		Map<String, Object> correctRatioMap = categoryDetailMapper.getCorrectRatio(catNo, userNo);

	    		// 값이 null이 아니고 BigDecimal 타입이면 double로 변환
	    		Double correctRatio = 0.0;
	    		if (correctRatioMap != null && correctRatioMap.get("correctRatio") != null) {
	    		    Object correctRatioObj = correctRatioMap.get("correctRatio");
	    		    if (correctRatioObj instanceof BigDecimal) {
	    		        correctRatio = ((BigDecimal) correctRatioObj).doubleValue();
	    		    } else if (correctRatioObj instanceof Number) {
	    		        correctRatio = ((Number) correctRatioObj).doubleValue();
	    		    }
	    		}
	    		
	    		// 작성자 수
	    		Map<String, Object> authorCountMap = categoryDetailMapper.getAuthorCount(catNo);
	    		Integer authorCount = (authorCountMap != null) ? ((Number) authorCountMap.get("authorCount")).intValue() : 0;
	    		categoryMap.put("authorCount", authorCount);
	    		
	    		// 좋아요 수
	    		Map<String, Object> favoriteCountMap = categoryDetailMapper.getFavoriteCount(catNo);
	    		Integer favoriteCount = (favoriteCountMap != null) ? ((Number) favoriteCountMap.get("favoriteCount")).intValue() : 0;
	    		categoryMap.put("favoriteCount", favoriteCount);
	    		
	    		return categoryMap;
	    	}).collect(Collectors.toList());
	        result.add(categoryList); // 결과 리스트에 추가
	    }
	    return result;
	}
	
}
