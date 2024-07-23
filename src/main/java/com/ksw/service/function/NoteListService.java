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
//	——
//	문제 리스트 컨트롤러 만들기
//	- 내 문제 / 조회한 문제 / 카테고리 내 문제 리스트 (요청 타입) 받아야함
//	- 카테고리 이름 출력
//	- 제목 출력
//	- 작성 시간출력
//	- 좋아요 수 출력
//	- 댓글 수 출력
//	- 페이징
//	- 검색바 구현
	
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
	
	
	// noteType : 1 - 나의 문제 / 2 - 틀린 문제 / 3 - 맞은 문제 / 4 - 특정 카테고리 문제 / 5 - 조회한 문제 / 6 - 북마크한 문제 / 7 - 즐겨찾기 목록
	public List<Map<String, Object>> getNoteList(
			UserVO userVO,
			Integer noteType, 
	        String categoryTitle
			){
		
		// 나의 문제 -> noteUser 
		// 틀린 문제 -> noteAnswer
		// 맞은 문제 -> noteAnswer
		// 특정 카테고리 문제 -> noteCategory
		// 조회한 문제 -> noteView
		// 북크한 문제 -> favoriteNote
		
		// 각각 항목 필요 요소	
		//	    - 카테고리 이름 출력
		//		- 제목 출력
		//		- 작성 시간출력
		//		- 좋아요 수 출력
		//		- 댓글 수 출력
		//		- 페이징
		//		- 검색바 구현
		
	    List<Map<String, Object>> result;
	    switch (noteType) {
	        case 1:
	            result = noteUserService.getNoteListByUserNo(userVO.getUserNo());
	            break;
	        case 2:
	            result = answerHistoryService.getNoteListByUserNoAndAnswerType(userVO.getUserNo(), 1);
	            break;
	        case 3:
	            result = answerHistoryService.getNoteListByUserNoAndAnswerType(userVO.getUserNo(), 2);
	            break;
	        case 4:
	            result = noteCategoryService.getNoteListByCategoryTitle(categoryTitle);
	            break;
	        case 5:
	            result = noteViewService.getNoteListByUserNo(userVO.getUserNo());
	            break;
	        case 6:
	            result = favoriteNoteService.getNoteListByUserNo(userVO.getUserNo());
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
