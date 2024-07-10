package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ksw.service.forObject.entity.AnnouncementService;


@Controller
public class AnnouncementController 
{

	private final AnnouncementService announcementService;
	
	@Autowired
	public AnnouncementController(AnnouncementService announcementService)
	{
		this.announcementService = announcementService;
	}

	// 공지사항 목록으로 이동
	@GetMapping("/noticelist")
	public String toNoticeListPage() 
	{
		return "announcement_list";
	}
	
	// 글쓰기 화면
	@RequestMapping(value = "/noticewrite")
	public String Write(HttpServletRequest request) 
	{
		if(request.getSession().getAttribute("login") == null)
		{
			//로그인 하지 않은 경우
			return "redirect:index.jsp";
		}
		return "announcement_write";
	}

	
}
 
