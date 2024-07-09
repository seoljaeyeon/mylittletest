package com.ksw.mylittletest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ksw.service.forObject.entity.AnnouncementService;
import com.ksw.vo.forObject.entity.AnnouncementVO;


@Controller
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;

	/*
	// 공지사항 목록으로 이동
	@GetMapping("/noticelist")
	public String toNoticeListPage() {
		return "announcement_list";
	}
	
	@GetMapping("/noticewrite")
	public String write() {
		return "announcement_write";
	}
	
	
	@PostMapping("/noticeview")
	public String insert(@ModelAttribute AnnouncementVO vo) throws Exception{
		announcementService.create(vo);
		return "redirect:noticelist";
	} 
	
	@PostMapping
	public String update(@ModelAttribute AnnouncementVO vo) throws Exception{
		announcementService.update(vo);
		return "redirect:noticelist";
	}
	
	@PostMapping
	public String delete(@RequestParam int announcementNo) throws Exception{
		announcementService.delete(announcementNo);
		return "redirect:noticelist";
	}
	*/
}
 
