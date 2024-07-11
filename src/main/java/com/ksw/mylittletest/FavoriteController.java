package com.ksw.mylittletest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.object.entity.Favorite;
import com.ksw.service.forObject.entity.FavoriteService;
import com.ksw.service.forObject.relation.FavoriteNoteService;

@Controller
public class FavoriteController {

	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private FavoriteNoteService favoriteNoteService;
	
	/*
	 * @PostMapping("/favorite") public String favorite(
	 * 
	 * @RequestParam("userNo") Integer userNo,
	 * 
	 * @RequestParam("noteNo") Integer noteNo, RedirectAttributes
	 * redirectAttributes) {
	 * 
	 * try { // favoriteNote 테이블에 데이터 기록 // favorite 테이블에 데이터 기록
	 * favoriteNoteService.toggleFavorite(userNo, noteNo);
	 * 
	 * redirectAttributes.addFlashAttribute("message", "성공"); } catch (Exception e)
	 * { redirectAttributes.addFlashAttribute("error", "실패"); }
	 * 
	 * return "redirect:/view?" + noteNo; }
	 */
}
