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
public class CategoryDetailService {
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
	
	public List<Map<String, Object>> getCategorySummary(Integer userNo, Integer menuType, Integer limit, Integer offset) {
		List<Map<String,Object>> categoryLists = new ArrayList<>();
		
		if(menuType == 0) {
			categoryLists = noteViewService.getCategoryListOrderedByNoteView();
		}else if (menuType == 1) {
        	categoryLists = noteUserService.getCategoryListByUserNo(userNo);
        } else if (menuType == 2) {
        	categoryLists = answerHistoryService.getCategoryListByUserNoAndAnswerType(userNo, 2);
        } else if (menuType == 3) {
        	categoryLists = answerHistoryService.getCategoryListByUserNoAndAnswerType(userNo, 1);
        } else if (menuType == 4) {
        	categoryLists = noteViewService.getTodayCategoryListByUserNo(userNo);
        } else if (menuType == 5) {
        	categoryLists = favoriteNoteService.getCategoryListByUserNoAndFavoriteType(userNo, 2);
        }
		
		categoryLists.stream().map(
				categoryMap -> {
		    		Map<String, Object> correctRatioMap = categoryDetailMapper.getCorrectRatio((Integer)categoryMap.get("categoryNo"), userNo);

		    		Double correctRatio = 0.0;
		    		if (correctRatioMap != null && correctRatioMap.get("correctRatio") != null) {
		    		    Object correctRatioObj = correctRatioMap.get("correctRatio");
		    		    if (correctRatioObj instanceof BigDecimal) {
		    		        correctRatio = ((BigDecimal) correctRatioObj).doubleValue();
		    		    } else if (correctRatioObj instanceof Number) {
		    		        correctRatio = ((Number) correctRatioObj).doubleValue();
		    		    }
		    		}
		    		
		    		categoryMap.put("correctRatio", correctRatio);
		    		
		    		// 작성자 수
		    		Map<String, Object> authorCountMap = categoryDetailMapper.getAuthorCount((Integer)categoryMap.get("categoryNo"));
		    		Integer authorCount = (authorCountMap != null) ? ((Number) authorCountMap.get("authorCount")).intValue() : 0;
		    		categoryMap.put("authorCount", authorCount);
		    		
		    		// 좋아요 수
		    		Map<String, Object> favoriteCountMap = categoryDetailMapper.getFavoriteCount((Integer)categoryMap.get("categoryNo"));
		    		Integer favoriteCount = (favoriteCountMap != null) ? ((Number) favoriteCountMap.get("favoriteCount")).intValue() : 0;
		    		categoryMap.put("favoriteCount", favoriteCount);

		    		return categoryMap;
				}).collect(Collectors.toList());
		
		return categoryLists;
		
//	    for(Map<String, Object> categorymap : categoryLists) {
//	    	Integer categoryNo = (Integer) categorymap.get("categoryNo");
//	    	List<Map<String, Object>> categoryList = categoryDetailMapper.getCategoryWithNoteCount(categoryNo, limit, offset);
//	    	
//	    	// 리스트의 각 map들에 대해서 
//	    	categoryList.stream()
//	    	.map(categoryMap -> {
//	    		Integer catNo = (Integer) categoryMap.get("categoryNo");
//	    		
//	    		// Category 객체를 직접 저장
//	    		Category category = new Category();
//	    		category.setCategoryNo((Integer) categoryMap.get("categoryNo"));
//	    		category.setCategoryTitle((String) categoryMap.get("categoryTitle"));
//	    		category.setCreatedAt((Timestamp) categoryMap.get("createdAt"));
//	    		category.setIsActive((Boolean) categoryMap.get("isActive"));
//	    		categoryMap.put("category", categoryService.convertToVO(categoryService.convertToDTO(category)));
//	    		
//	    		// 문제 수
//	    		Integer noteCount = ((Number) categoryMap.get("noteCount")).intValue();
//	    		categoryMap.put("noteCount", noteCount);
//	    		
//	    		Map<String, Object> correctRatioMap = categoryDetailMapper.getCorrectRatio(categoryNo, userNo);
//
//
//	    		Double correctRatio = 0.0;
//	    		if (correctRatioMap != null && correctRatioMap.get("correctRatio") != null) {
//	    		    Object correctRatioObj = correctRatioMap.get("correctRatio");
//	    		    if (correctRatioObj instanceof BigDecimal) {
//	    		        correctRatio = ((BigDecimal) correctRatioObj).doubleValue();
//	    		    } else if (correctRatioObj instanceof Number) {
//	    		        correctRatio = ((Number) correctRatioObj).doubleValue();
//	    		    }
//	    		}
//	    		
//	    		categoryMap.put("correctRatio", correctRatio);
//	    		
//	    		// 작성자 수
//	    		Map<String, Object> authorCountMap = categoryDetailMapper.getAuthorCount(catNo);
//	    		Integer authorCount = (authorCountMap != null) ? ((Number) authorCountMap.get("authorCount")).intValue() : 0;
//	    		categoryMap.put("authorCount", authorCount);
//	    		
//	    		// 좋아요 수
//	    		Map<String, Object> favoriteCountMap = categoryDetailMapper.getFavoriteCount(catNo);
//	    		Integer favoriteCount = (favoriteCountMap != null) ? ((Number) favoriteCountMap.get("favoriteCount")).intValue() : 0;
//	    		categoryMap.put("favoriteCount", favoriteCount);
//	    		
//	    		return categoryMap;
//	    	}).collect(Collectors.toList());
//	    }
//	    return categoryList;
		
		
	}
}
