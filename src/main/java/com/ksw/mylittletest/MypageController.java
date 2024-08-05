package com.ksw.mylittletest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ksw.dto.forObject.entity.FileDTO;
import com.ksw.service.forObject.entity.AlarmService;
import com.ksw.service.forObject.relation.AlarmRelationService;
import com.ksw.service.forObject.relation.FileUserService;
import com.ksw.service.function.AuthService;
import com.ksw.service.function.NoteListService;
import com.ksw.service.function.SearchService;
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
	@Autowired
	private NoteListService noteListService;
	@Autowired
	private FileUserService fileUserService;
	@Autowired
	private SearchService searchService;
	
	@GetMapping
	public String myPageMain() {
		return "redirect:/mypage/alarm";
	}
	
//	@GetMapping("/notelist/{noteType}")
//	public String toTheNoteList(
//			Model model,
//			HttpSession session,
//			@PathVariable("noteType") Integer noteType
//			) {
//		
//	    List<Map<String, Object>> result = (List<Map<String, Object>>) session.getAttribute("result");
//	    if (result == null) {
//	        return "redirect:/mylittletest/error";
//	    }
//
//	    // 모델에 데이터 저장
//	    model.addAttribute("result", result);
//	    model.addAttribute("noteType", noteType);
//
//	    // 세션에서 데이터 삭제
//	    session.removeAttribute("noteResult");
//	    System.out.println("세션에서 삭제 완료 : " + result.get(0).get("categoryTitle"));
//	    return "mynotelist";
//	}
	
	@GetMapping("/alarm")
	public String toTheAlarmList(
			Model model,
	        @RequestParam(value = "page", defaultValue = "1") Integer page 
			) {
		
		UserVO userVO = authService.getUserVO();
		if(userVO == null) {
			return "redirect:/login";
		}
		
		// 파일 정보를 가져오는 로직
	    FileDTO fileDTO = fileUserService.getFileByUserNo(userVO.getUserNo());
	    String profilePictureUrl = null;
	    if (fileDTO != null && fileDTO.getSavedName() != null) {
	        profilePictureUrl = "/mylittletest/uploads/" + fileDTO.getSavedName();
	    } else {
	        profilePictureUrl = "https://www.rollingstone.com/wp-content/uploads/2020/07/Screen-Shot-2020-07-15-at-11.24.37-AM.jpg"; // 기본 프로필 이미지
	    }
	    model.addAttribute("profileURL", profilePictureUrl);

		// 분류, 내용, 시간
		Integer limit = 10;
		Integer offset = (page-1)*limit;
		List<Map<String,Object>> result = alarmRelationService.getAlarmSummary(userVO.getUserNo(), limit, offset);
		
		model.addAttribute("userVO", userVO);
		model.addAttribute("AlarmList", result);
		
		return "mypage_alarm";
	}
	
	@GetMapping("/bookmark")
	public String toTheBookmark(
			Model model,
	        @RequestParam(value = "page", defaultValue = "1") Integer page 
) {
	
		UserVO userVO = authService.getUserVO();
		if(userVO == null) {
			return "redirect:/login";
		}
		
		// 파일 정보를 가져오는 로직
	    FileDTO fileDTO = fileUserService.getFileByUserNo(userVO.getUserNo());
	    String profilePictureUrl = null;
	    if (fileDTO != null && fileDTO.getSavedName() != null) {
	        profilePictureUrl = "/mylittletest/uploads/" + fileDTO.getSavedName();
	    } else {
	        profilePictureUrl = "https://www.rollingstone.com/wp-content/uploads/2020/07/Screen-Shot-2020-07-15-at-11.24.37-AM.jpg"; // 기본 프로필 이미지
	    }
	    model.addAttribute("profileURL", profilePictureUrl);
		
	    Boolean search = (Boolean) model.asMap().get("search");
	    String searchInput = (String) model.asMap().get("searchInput");
		
		Integer limit = 10;
		Integer offset = (page-1)*limit;
		
		List<Map<String,Object>> list = new ArrayList<>();
		if((search != null) ? (Boolean) search : false) {
			System.out.println("검색결과 출력 성공");
	    	list = searchService.searchMypage(userVO.getUserNo(), limit, offset, searchInput);
	    	model.addAttribute("list", list);
		} else {
			list = noteListService.getFavoriteList(userVO, limit, offset);
			model.addAttribute("list", list);
		}
		model.addAttribute("userVO", userVO);
		return "mypage_bookmark";
	}
	
	
	
}
