package com.ksw.service.function;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.service.forObject.relation.AnswerHistoryService;
import com.ksw.service.forObject.relation.FavoriteNoteService;
import com.ksw.service.forObject.relation.NoteCategoryService;
import com.ksw.service.forObject.relation.NoteUserService;
import com.ksw.service.forObject.relation.NoteViewService;
import com.ksw.vo.forObject.entity.UserVO;

@Service
public class NoteListService {
	
	@Autowired
	private NoteUserService noteUserService;
	@Autowired
	private AnswerHistoryService answerHistoryService;
	@Autowired
	private NoteCategoryService noteCategoryService;
	@Autowired
	private NoteViewService noteViewService;
	@Autowired
	private FavoriteNoteService favoriteNoteService;
	
	public List<Map<String, Object>> getFavoriteList(
			UserVO userVO,
			Integer limit,
			Integer offset) {
		
		List<Map<String, Object>> result = favoriteNoteService.getFavoriteListByUserNo(userVO.getUserNo(), limit, offset);
		return result;
	}
	
	public List<Map<String, Object>> getNoteList(
			UserVO userVO,
			String menuPath, 
			String sort,
	        String categoryTitle,
	        Integer page,
	        Integer limit,
	        Integer searchType,
	        String searchInput
			){
		
		// 메뉴path 종류
			// 내가 쓴 문제 : mytest
			// 전체 문제 : allcategory
			// 틀린 문제 : correctmytest
			// 맞은 문제 : reviewmytest
			// 오늘 본 문제 : todayquestions
			// 북마크&좋아요 문제 : - 일단 나중에
		
		// 나의 문제 -> noteUser 
		// 틀린 문제 -> noteAnswer
		// 맞은 문제 -> noteAnswer
		// 특정 카테고리 문제 -> noteCategory
		// 조회한 문제 -> noteView
		// 북크한 문제 -> favoriteNote
		
	    List<Map<String, Object>> result;
	    
	    Integer offset = (page-1)*limit;
	    
	    switch (menuPath) {
	        case "mytest": // 내가 쓴 게시글 전부 다 가져오기
	            result = noteUserService.getNoteList(userVO.getUserNo(), sort, limit, offset, searchType, searchInput);
	            break;
	        case "reviewmytest": // 내가 맞은 게시물 다 가져오기
	            result = answerHistoryService.getNoteListByUserNoAndAnswerType(userVO.getUserNo(), 2, sort, limit, offset, searchType, searchInput);
	            break;
	        case "correctmytest":
	            result = answerHistoryService.getNoteListByUserNoAndAnswerType(userVO.getUserNo(), 1, sort, limit, offset, searchType, searchInput);
	            break;
	        case "allcategory":
	            result = noteCategoryService.getNoteListByCategoryTitle(categoryTitle, sort, limit, offset, searchType, searchInput);
	            break;
	        case "todayquestions":
	            result = noteViewService.getNoteListByUserNo(userVO.getUserNo(), sort, limit, offset, searchType, searchInput);
	            break;
	        case "bookmarkquestions":
	            result = favoriteNoteService.getFavoritedNoteListByUserNo(userVO.getUserNo(), sort, limit, offset, searchType, searchInput);
	            break;
	        default:
	        	result = null;
	    }
	    
	    // result 갖고있는 목록
//	    	- categoryTitle
//	    	- noteTitle
//	    	- createdAt
//	    	- noteNo
	    //  - favorite_count
	    //  - reply_count

	    return result;
    }
}
