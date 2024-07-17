package com.ksw.mylittletest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksw.service.forObject.entity.FavoriteService;
import com.ksw.service.forObject.relation.FavoriteNoteService;
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
	
	
    @PostMapping(value = "/favorite", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> doFavorite(
			@RequestParam Integer noteNo,
			@RequestParam Integer requestType, // 요청 유형 확인 -> 1:좋아요 2:북마크 -1:덜보기 -2:차단
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
		
		// 파라미터 값 검
		if (noteNo == null || requestType == null || targetType == null) {
			response.put("status", "parameter_null");
			return response;
		}
		
		if (targetType == 1) {
			
		}
		
		if (targetType == 2) {
			
		}
		
		if (targetType == 3) {
			
		}
		
    	return response;
    }
}
