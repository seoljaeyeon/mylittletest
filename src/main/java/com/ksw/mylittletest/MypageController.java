package com.ksw.mylittletest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ksw.service.forObject.entity.AlarmService;
import com.ksw.service.forObject.relation.AlarmRelationService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	private AlarmService alarmService;
	@Autowired
	private AlarmRelationService alarmRelationService;
	@Autowired
	private AuthService authService;
	
	
	@GetMapping
	public String myPageMain() {
		return "redirect:/mypage/alarm";
	}
	
	@GetMapping("/notelist/{noteType}")
	public String toTheNoteList(
			Model model,
			HttpSession session,
			@PathVariable("noteType") Integer noteType
			) {
		
	    List<Map<String, Object>> result = (List<Map<String, Object>>) session.getAttribute("result");
	    if (result == null) {
	        return "redirect:/mylittletest/error";
	    }

	    // 모델에 데이터 저장
	    model.addAttribute("result", result);
	    model.addAttribute("noteType", noteType);

	    // 세션에서 데이터 삭제
	    session.removeAttribute("noteResult");
	    System.out.println("세션에서 삭제 완료 : " + result.get(0).get("categoryTitle"));
	    return "mynotelist";
	}
	
	@GetMapping("/alarm")
	public String toTheAlarmList(
			Model model,
	        @RequestParam(value = "page", defaultValue = "1") Integer page 
			) {
		
		UserVO userVO = authService.getUserVO();
		if(userVO == null) {
			return "redirect:/login";
		}
		
		// 분류, 내용, 시간
		Integer limit = 10;
		Integer offset = (page-1)*limit;
		List<List<Map<String,Object>>> result = alarmRelationService.getAlarmSummary(userVO.getUserNo(), limit, offset);
		
		model.addAttribute("userVO", userVO);
		model.addAttribute("AlarmList", result);
		
		return "mypage_alarm";
	}
	
}
