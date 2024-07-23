package com.ksw.mylittletest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SearchController {


	@PostMapping("/search")
	@Transactional
	public String search(
			@RequestBody Map<String, Object> requestData,
			RedirectAttributes redirectAttribute
			) {
		
		String urlPath = (String) requestData.get("urlPath");
		String searchInput = (String) requestData.get("searchInput");
		
	    if (searchInput == null || searchInput.trim().isEmpty()) {
	        // searchInput이 빈 문자열이면 원래의 urlPath로 리다이렉트
	        return "redirect:" + urlPath;
	    }
		
		try {
			searchInput = URLEncoder.encode(searchInput, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			return "redirect:"+urlPath;
		}
		
		redirectAttribute.addFlashAttribute("search", true);
		redirectAttribute.addFlashAttribute("searchInput", searchInput);
		
		return "redirect:"+urlPath;
	}
}
