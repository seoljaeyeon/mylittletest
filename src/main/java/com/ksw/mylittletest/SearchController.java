package com.ksw.mylittletest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SearchController {


	@PostMapping("/search")
	public String search(
			@RequestParam( value = "urlPath", required = false) String urlPath,
			@RequestParam( value = "searchInput", required = false) String searchInput,
			RedirectAttributes redirectAttribute
			) {

		System.out.println("검색창 진입 --- "+  urlPath);
		System.out.println("검색창 진입 --- "+  searchInput);
		
		String basePath = "/mylittletest";
		if (urlPath.startsWith(basePath)) {
			urlPath = urlPath.substring(basePath.length());
		}		
		
	    if (searchInput == null || searchInput.trim().isEmpty()) {
	        // searchInput이 빈 문자열이면 원래의 urlPath로 리다이렉트
	        return "redirect:" + urlPath;
	    }
		
		try {
			searchInput = URLEncoder.encode(searchInput, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			return "redirect:"+urlPath;
		}
		
		System.out.println(searchInput);
		
		redirectAttribute.addFlashAttribute("search", true);
		redirectAttribute.addFlashAttribute("searchInput", searchInput);
		
		return "redirect:"+urlPath;
	}
}
