package com.ksw.mylittletest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

	@GetMapping("/category")
	public String categoryMain() {
		return "questionlist.jsp";
	}
	
}
