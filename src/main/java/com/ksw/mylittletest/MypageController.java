package com.ksw.mylittletest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MypageController {

	@GetMapping("/mypage/notelist/{noteType}")
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
}
