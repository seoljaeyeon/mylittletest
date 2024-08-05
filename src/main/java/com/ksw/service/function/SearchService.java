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
	
	public List<Map<String, Object>> search(Integer userNo, Integer menuType, Integer page, String searchInput) {
		
		Integer limit = 10;
		Integer offset = (page-1)*limit;
		
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
	}
	
}
