package com.ksw.service.function;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.function.CategoryDetailMapper;
import com.ksw.object.entity.Category;
import com.ksw.service.forObject.entity.CategoryService;

@Service
public class CategoryDetailService {
	@Autowired
    private CategoryDetailMapper categoryDetailMapper;
	@Autowired
	private CategoryService categoryService;

	public List<Map<String, Object>> getCategorySummary(Integer categoryNo, Integer userNo, Integer limit, Integer offset) {
		// 카운트 / category 객체 로 이루어진 리스트 반환
	    List<Map<String, Object>> categoryList = categoryDetailMapper.getCategoryWithNoteCount(categoryNo, limit, offset);

	    // 리스트의 각 map들에 대해서 
	    return categoryList.stream()
	    		// 
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
	        Double correctRatio = (correctRatioMap != null) ? (Double) correctRatioMap.get("correctRatio") : 0.0;
	        categoryMap.put("correctRatio", correctRatio);

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
	}
}
