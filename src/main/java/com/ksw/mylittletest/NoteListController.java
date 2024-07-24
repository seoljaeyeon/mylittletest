package com.ksw.mylittletest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class NoteListController {
	
	@Autowired
	private AuthService authService;

	@GetMapping("/notelist")
	public String toNoteList() {
		return "redirect:/notelist";
	}
	
	// 내가 쓴 문제
	// 전체 문제
	// 틀린 문제
	// 맞은 문제
	// 오늘 본 문제
	// 검색바 - 카테고리 : 내용
	
	@PostMapping(value = "/notelist/{menuPath}/{sort}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> filteredList(
			@RequestBody Map<String, Object> DataFromRequest
			){
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		UserVO userVO = authService.getUserVO();
		if(userVO == null) {
			response.put("status", "login_needed");
			response.put("url", "/mylittletest/login");
			return response;
		}
		
		String menuPath = (String) DataFromRequest.get("menuPath");
		String sort = (String) DataFromRequest.get("sort");
		
		if(menuPath == null || sort == null || menuPath.isEmpty() || sort.isEmpty()) {
			response.put("status", "parameter_null");
			return response;
		}
		
		
		
		return response;
	}
	
	
}
