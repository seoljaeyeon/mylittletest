package com.ksw.mylittletest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.service.function.AuthService;
import com.ksw.service.function.NoteListService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
public class NoteListController {
	
	@Autowired
	private AuthService authService;
	@Autowired
	private NoteListService noteListService;
	
	@GetMapping("/notelist")
	public String toNoteList(
			RedirectAttributes redirectAttributes,
			Model model,
			HttpSession session
			) {
		
//		UserVO userVO = authService.getUserVO();
//		
//		if(userVO == null) {
//			return "redirect:/login";
//		}
//		
//		model.addAttribute("userVO", userVO);
//
//		List<Map<String, Object>> result = (List<Map<String, Object>>) model.asMap().get("result");
//		
//		
//		String menuPath = (String) model.asMap().get("menuPath");
//		if (menuPath == null || menuPath.isEmpty()) {
//			menuPath = "mytest";
//		}
//		
//		String sort =(String) model.asMap().get("sort");
//		if (sort == null || sort.isEmpty()) {
//			sort = "createdAt";
//		}
//		
//		Integer page = (Integer) model.asMap().get("page");
//		if (page == null) {
//			page = 1;
//		}
//		
//		Integer limit = (Integer) model.asMap().get("limit");
//		if (limit == null) {
//			limit = 10;
//		}
//		String categoryTitle = (String) model.asMap().get("categoryTitle");
//		if (categoryTitle == null || categoryTitle.isEmpty()) {
//			categoryTitle = null;
//		}
//		
//		String searchInput = (String) model.asMap().get("searchInput");
//		Integer searchType = (Integer) model.asMap().get("searchType");
//		
//		if(searchType == null) {
//			searchType = 1;
//		}
//		
//		if(result == null || result.isEmpty()) {
//			result = noteListService.getNoteList(userVO, menuPath, sort, categoryTitle, page, limit, searchType, searchInput);
//		}
//		

        
        UserVO userVO = authService.getUserVO();
        
        if (userVO == null) {
            return "redirect:/login";
        }

        model.addAttribute("userVO", userVO);

        // 세션에서 값 가져오기
        String menuPath = (String) session.getAttribute("menuPath");
        String sort = (String) session.getAttribute("sort");
        Integer page = (Integer) session.getAttribute("page");
        Integer limit = (Integer) session.getAttribute("limit");
        String categoryTitle = (String) session.getAttribute("categoryTitle");
        String searchInput = (String) session.getAttribute("searchInput");
        Integer searchType = (Integer) session.getAttribute("searchType");
        List<Map<String, Object>> result = (List<Map<String, Object>>) session.getAttribute("result");

        System.out.println("get요청 menuPath ------- " + menuPath);
        System.out.println("get요청 sort ------- " + sort);
        System.out.println("get요청 page ------- " + page);
        System.out.println("get요청 limit ------- " + limit);
        System.out.println("get요청 categoryTitle ------- " + categoryTitle);
        System.out.println("get요청 result ------- " + result);
        System.out.println("get요청 searchInput ------- " + searchInput);
        System.out.println("get요청 searchType ------- " + searchType);
        
        // 초기 값 설정
        if (menuPath == null || menuPath.isEmpty()) {
            menuPath = "mytest";
        }
        if (sort == null || sort.isEmpty()) {
            sort = "createdAt";
        }
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }
        if (result == null) {
            result = noteListService.getNoteList(userVO, menuPath, sort, categoryTitle, page, limit, searchType, searchInput);
        }
		
        // 세션에서 값을 제거 (필요에 따라 유지할 수도 있습니다)
        session.removeAttribute("menuPath");
        session.removeAttribute("sort");
        session.removeAttribute("page");
        session.removeAttribute("limit");
        session.removeAttribute("categoryTitle");
        session.removeAttribute("result");
        session.removeAttribute("searchInput");
        session.removeAttribute("searchType");
        
		redirectAttributes.addFlashAttribute("menuPath", menuPath);
		redirectAttributes.addFlashAttribute("sort", sort);
		redirectAttributes.addFlashAttribute("page", page);
		redirectAttributes.addFlashAttribute("limit", limit);
		redirectAttributes.addFlashAttribute("categoryTitle", categoryTitle);
        redirectAttributes.addFlashAttribute("result", result);
        redirectAttributes.addFlashAttribute("searchInput", searchInput);
        redirectAttributes.addFlashAttribute("searchType", searchType);
        
		model.addAttribute("menuPath", menuPath);
		model.addAttribute("sort", sort);
		model.addAttribute("page", page);
		model.addAttribute("limit", limit);
		model.addAttribute("categoryTitle", categoryTitle);
		model.addAttribute("result", result);
		model.addAttribute("searchInput", searchInput);
		model.addAttribute("searchType", searchType);
		
  
		
		return "mynotelist";
	}
	
	
	
	// 내가 쓴 문제
	// 전체 문제
	// 틀린 문제
	// 맞은 문제
	// 오늘 본 문제
	// 검색바 - 카테고리 : 내용
	
	// FETCH로 요청할 메소드
	@PostMapping(value = "/notelist", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> filteredList(
			@RequestBody Map<String, Object> DataFromRequest,
            RedirectAttributes redirectAttributes,
            HttpSession session
			){
		
        System.out.println("post요청 진입 ------- ");

		
		Map<String, Object> response = new HashMap<String, Object>();
		
		UserVO userVO = authService.getUserVO();
		if(userVO == null) {
			response.put("status", "login_needed");
			response.put("url", "/mylittletest/login");
			System.out.println("post요청 튕김1 ------- ");
			return response;
		}
		
		String menuPath = (String) DataFromRequest.get("menuPath");
		String sort = (String) DataFromRequest.get("sort");
		
		if(menuPath == null || menuPath.isEmpty()) {
			
			System.out.println("post요청 튕김2 ------- ");
			menuPath = "mytest";
		}
		
		Integer page = (Integer) DataFromRequest.get("page");
		Integer limit = (Integer) DataFromRequest.get("limit");
		
		if(page == null) {
			page = 1;
		}
		
		if(limit == null) {
			limit = 10;
		}
		
		if(sort == null) {
			sort = "createdAt";
		}
		String searchInput = (String) DataFromRequest.get("searchInput");
		Integer searchType = (Integer) DataFromRequest.get("searchType");
		
		if(searchType == null) {
			searchType = 1;
		}
		
		String categoryTitle = (String) DataFromRequest.get("categoryTitle");

		List<Map<String, Object>> result = noteListService.getNoteList(userVO, menuPath, sort, categoryTitle, page, limit, searchType, searchInput);
		
		
		session.setAttribute("menuPath", menuPath);
		session.setAttribute("sort", sort);
		session.setAttribute("page", page);
		session.setAttribute("limit", limit);
		session.setAttribute("categoryTitle", categoryTitle);
		session.setAttribute("result", result);
		session.setAttribute("searchInput", searchInput);
		session.setAttribute("searchType", searchType);
        
        System.out.println("post요청 menuPath ------- " + menuPath);
        System.out.println("post요청 sort ------- " + sort);
        System.out.println("post요청 page ------- " + page);
        System.out.println("post요청 limit ------- " + limit);
        System.out.println("post요청 categoryTitle ------- " + categoryTitle);
        System.out.println("post요청 result ------- " + result);
        System.out.println("post요청 searchInput ------- " + searchInput);
        System.out.println("post요청 searchType ------- " + searchType);
        
		response.put("status", "success");
		response.put("url", "/mylittletest/notelist");
		
		return response;
	}
}
