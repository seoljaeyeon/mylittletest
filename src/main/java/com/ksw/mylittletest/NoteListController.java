package com.ksw.mylittletest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksw.service.forObject.relation.AnswerHistoryService;
import com.ksw.service.forObject.relation.FavoriteNoteService;
import com.ksw.service.forObject.relation.NoteCategoryService;
import com.ksw.service.forObject.relation.NoteUserService;
import com.ksw.service.forObject.relation.NoteViewService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class NoteListController {
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
	private AuthService authService;
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
	
	// noteType : 1 - 나의 문제 / 2 - 틀린 문제 / 3 - 맞은 문제 / 4 - 특정 카테고리 문제 / 5 - 조회한 문제 / 6 - 북마크한 문제 
	@PostMapping(value="/notelist/{noteType}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getNoteList(
			@PathVariable("noteType") Integer noteType, 
	        @RequestBody Map<String, String> requestParams,
			HttpSession session
			){
		
		Map<String, Object> response = new HashMap<>();
		if (requestParams.isEmpty()) {
			response.put("status", "data is empty");
			return response;
		}
		
		String categoryTitle = requestParams.get("categoryTitle");
		UserVO userVO = authService.getUserVO();
		if (userVO == null) {
			response.put("status", "login_needed");
			response.put("url", "/mylittletest/login");
		}
		
		Integer[] noteTypes = {1, 2, 3, 4, 5, 6};
		
		
		if (noteType == null || !Arrays.asList(noteTypes).contains(noteType)) {
			response.put("status", "wrong request");
			return response;
		}
		
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
		
		System.out.println("noteType : " + noteType + " categoryTitle : " + categoryTitle);
		
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
	            response.put("status", "wrong request");
	            return response;
	    }

	    // 데이터를 모델에 저장하고 리다이렉트
	    response.put("status", "success");
	    response.put("url", "/mylittletest/mypage/notelist/" + noteType);
	    session.setAttribute("result", result);
	    System.out.println(result.get(0).get("categoryTitle"));
	    return response;
    }
}
