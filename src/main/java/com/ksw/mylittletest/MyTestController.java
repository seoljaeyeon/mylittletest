package com.ksw.mylittletest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.service.forObject.entity.CategoryService;
import com.ksw.service.forObject.relation.CategoryViewService;
import com.ksw.service.forObject.relation.FavoriteNoteService;
import com.ksw.service.forObject.relation.NoteCategoryService;
import com.ksw.service.function.AuthService;
import com.ksw.service.function.QuestionService;
import com.ksw.service.function.SearchService;
import com.ksw.vo.forObject.entity.UserVO;
import com.ksw.vo.function.QuestionVO;

@Controller
@RequestMapping("/mytest")
public class MyTestController {
	
	@Autowired
	private AuthService authService;
	@Autowired
	private NoteCategoryService noteCategoryService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategoryViewService categoryViewService;
	@Autowired
	private SearchService searchService;
	@Autowired
	private FavoriteNoteService favoriteNoteService;
	
	
	@GetMapping
	public String toCategory(
			RedirectAttributes redirectAttribute) {
			Integer menuType = 1;
			redirectAttribute.addFlashAttribute("menuType", menuType);
		
		return "redirect:/mytest/category";
	}
	
	@GetMapping("/category")
	public String viewCategoryPage(
			RedirectAttributes redirectAttributes,
            @RequestParam(value="page", required = false, defaultValue="1") Integer page,
            Model model
			){
	    
		Integer menuType = (Integer) model.asMap().get("menuType");
		String  menuName = "mytest";
		
	    Boolean search = (Boolean) model.asMap().get("search");
	    String searchInput = (String) model.asMap().get("searchInput");
	    
		if (search != null || searchInput != null) {
			redirectAttributes.addFlashAttribute("search", search);
			redirectAttributes.addFlashAttribute("searchInput", searchInput);
		}
		
		// menuType이 null인 경우 처리			
		if (menuType == null) {
			menuType = 1;
			redirectAttributes.addFlashAttribute("menuType", menuType);
			
			return "redirect:/category";
		}
		
		UserVO userVO = authService.getUserVO();
		if (userVO == null) {
			return "redirect:/login";
		}
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
		
		//최근 조회한 카테고리 목록 (오늘)
		List<Map<String,Object>> recent_category = categoryViewService.getTodayCategoryView(userVO.getUserNo(), menuType);
		model.addAttribute("recent_categories", recent_category);
		
	    System.out.println("from Search? " + search );
	    
	    List<List<Map<String, Object>>> list = new ArrayList<>();
	    if((search != null) ? (Boolean) search : false) {
	    	list = searchService.search(userVO.getUserNo(), menuType, page, searchInput);
	    	model.addAttribute("list", list);
	    	model.addAttribute("menuName", menuName);
	    } else {
	    	list = categoryService.getListByViewOrder(userVO.getUserNo(), menuType, page);
	    	model.addAttribute("list", list);
	    	model.addAttribute("menuName", menuName);
	    };
				
		return "questionlist";
	}
	
	@GetMapping("/category/{categoryTitle}")
	public String getRandom(
			@PathVariable("categoryTitle") String categoryTitle, 
			HttpSession session,
			Model model,
			HttpServletRequest request) {
		
        Optional<UserVO> auth = Optional.ofNullable(authService.getUserVO());
        if (!auth.isPresent()) {
            return "redirect:/login";
        }
		
		UserVO userVO = auth.get();
		Integer menuType = 1;
	    String  menuName = "mytest";


		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
		
		//카테고리 조회 수 증가
		categoryViewService.categoryViewIncrease(categoryTitle, request, session);
		// 사용자가 작성한 문제 중, 사용자가 보지 못한 문제 중에서 랜덤문제 출제
		// 보지 못한 문제가 없다면 본 문제 중에서 랜덤으로 출제
		Integer random = noteCategoryService.getRandomNobyCategoryTitle(categoryTitle, userVO.getUserNo(), menuType);
		if (random == null || random == 0) {
			// 추가적인 처리 또는 오류 페이지로 리다이렉트
			return "redirect:/"+menuName+"/category";
		}		
        try {
			categoryTitle = URLEncoder.encode(categoryTitle, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "redirect:/"+menuName+"/category";
		}
		return "redirect:/"+menuName+"/category/"+categoryTitle+"/"+random;
	}
	
	@GetMapping("/category/{categoryTitle}/{noteNo}")
	@Transactional
	public String viewPage(
			@PathVariable("noteNo") Integer noteNo,
			@PathVariable("categoryTitle") String categoryTitle,
			Model model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes,
			HttpSession session) { 
		
        Optional<UserVO> auth = Optional.ofNullable(authService.getUserVO());
        if (!auth.isPresent()) {
            return "redirect:/login";
        }
		
      
		UserVO userVO = auth.get();
		String menuName = "mytest";
		
		// 사용자 정보 저장
		model.addAttribute("userVO", userVO);
				
		// DB에서 문제 정보 가져오기 
		QuestionVO questionVO = questionService.Read(noteNo, userVO, request, session);

		// 모델에 문제 정보 세팅
		model.addAttribute("questionVO", questionVO);
		model.addAttribute("menuName", menuName);
		
		boolean isBlocked = favoriteNoteService.isBlocked(userVO.getUserNo(), noteNo);
		if (isBlocked) {
	        // 차단된 상태일 때 처리	
	        redirectAttributes.addFlashAttribute("isBlocked", true);
	        redirectAttributes.addFlashAttribute("message", "비활성화된 문제입니다");
	        return "redirect:/index";
	    }
		return "questionsolve"; 
	}
}
