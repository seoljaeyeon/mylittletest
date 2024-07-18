package com.ksw.mylittletest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksw.service.forObject.entity.FavoriteService;
import com.ksw.service.forObject.relation.FavoriteCategoryService;
import com.ksw.service.forObject.relation.FavoriteNoteService;
import com.ksw.service.forObject.relation.FavoriteReplyService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private FavoriteNoteService favoriteNoteService;
	@Autowired
	private AuthService authService;
	@Autowired
	private FavoriteCategoryService favoriteCategoryService;
	@Autowired
	private FavoriteReplyService favoriteReplyService;
	
	/*
	 * 요청하는 페이지에서 좋아요/북마크/댓글신고 버튼 ajax에서 보내야 하는 값
	 * 1. noteNo / 2. requestType / 3. targetType
	 * 
	 * - requestType 종류
	 * 0:기본(좋아요X) 1:좋아요 2:북마크 -1:덜보기 -2:차단
	 * 
	 * - targetType 종류 (버튼 종류)
	 * 1:문제 2:카테고리 3:댓글
	 *  
	 */
	
    @PostMapping(value = "/favorite", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> doFavorite(
			@RequestParam Integer noteNo,
			@RequestParam Integer requestType, 
			@RequestParam Integer targetType
			) {
		UserVO userVO = authService.getUserVO();
		
		Map<String, String> response = new HashMap<>();
		
		// 사용자 권한 체크 한번 더
		if (userVO == null || userVO.getUserNo() == null) {
			response.put("status", "loing_needed");
			response.put("url", "/mylittletest/login");
			return response;
		}
		
		// 파라미터 값 검사
		if (noteNo == null || requestType == null || targetType == null) {
			response.put("status", "parameter_null");
			return response;
		}
		
		Integer[] requestTypes = {0, 1, 2, -1, -2};
		
		
		if (!Arrays.asList(requestTypes).contains(requestType)) {
			response.put("status", "wrong request");
			return response;
		}
		
		if (targetType != 1 ) {
			response.put("status", "wrong request");
			return response;
		}
		
		// 삽입 성공 - requestType과 같은 숫자 반환 / 실패 - 100 반환
		Integer result = favoriteNoteService.updateFavorite(noteNo, userVO.getUserNo(), requestType);
		if(result<100) {
			response.put("status", "insert_success");
			response.put("favorite_status", result.toString());
		} else {
			response.put("status", "insert_failed");
		};
    	return response;
    }
    
    @PostMapping(value = "/bookmark", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> doBookmark(
			@RequestParam Integer categoryNo,
			@RequestParam Integer requestType, // 요청 유형 확인 -> 0:기본(좋아요X) 1:좋아요 2:북마크 -1:덜보기 -2:차단
			@RequestParam Integer targetType   // 요청 대상 유형 확인 -> 1:문제 2:카테고리 3:댓글
			) {
		UserVO userVO = authService.getUserVO();
		
		Map<String, String> response = new HashMap<>();
		
		// 사용자 권한 체크 한번 더
		if (userVO == null || userVO.getUserNo() == null) {
			response.put("status", "loing_needed");
			response.put("url", "/mylittletest/login");
			return response;
		}
		
		// 파라미터 값 검사
		if (categoryNo == null || requestType == null || targetType == null) {
			response.put("status", "parameter_null");
			return response;
		}
		
		Integer[] requestTypes = {0, 2, -2};
		
		
		if (!Arrays.asList(requestTypes).contains(requestType)) {
			response.put("status", "wrong request");
			return response;
		}
		
		if (targetType != 2 ) {
			response.put("status", "wrong request");
			return response;
		}
		
		// 삽입 성공 - requestType과 같은 숫자 반환 / 실패 - 100 반환
		Integer result = favoriteCategoryService.updateBookmark(categoryNo, userVO.getUserNo(), requestType);
		if(result<100) {
			response.put("status", "insert_success");
			response.put("favorite_status", result.toString());
		} else {
			response.put("status", "insert_failed");
		};
    	return response;
    }
    
    @PostMapping(value = "/replyBlock", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> replyBlock(
			@RequestParam Integer noteNo,
			@RequestParam Integer requestType, // 요청 유형 확인 -> 0:기본(좋아요X) 1:좋아요 2:북마크 -1:덜보기 -2:차단
			@RequestParam Integer targetType   // 요청 대상 유형 확인 -> 1:문제 2:카테고리 3:댓글
			) {
		UserVO userVO = authService.getUserVO();
		
		Map<String, String> response = new HashMap<>();
		
		// 사용자 권한 체크 한번 더
		if (userVO == null || userVO.getUserNo() == null) {
			response.put("status", "loing_needed");
			response.put("url", "/mylittletest/login");
			return response;
		}
		
		// 파라미터 값 검사
		if (noteNo == null || requestType == null || targetType == null) {
			response.put("status", "parameter_null");
			return response;
		}
		
		Integer[] requestTypes = {0, -2};
		
		if (!Arrays.asList(requestTypes).contains(requestType)) {
			response.put("status", "wrong request");
			return response;
		}
		
		if (targetType != 3) {
			response.put("status", "wrong request");
			return response;
		}
		
		// 삽입 성공 - requestType과 같은 숫자 반환 / 실패 - 100 반환
		Integer result = favoriteReplyService.updateFavorite(noteNo, userVO.getUserNo(), requestType);
		if(result<100) {
			response.put("status", "insert_success");
			response.put("favorite_status", result.toString());
		} else {
			response.put("status", "insert_failed");
		};
    	return response;
    }
    
    
}
