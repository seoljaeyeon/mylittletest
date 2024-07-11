package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.dto.forObject.entity.AnnouncementDTO;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.relation.AnnouncementUserDTO;
import com.ksw.service.forObject.entity.AnnouncementService;
import com.ksw.vo.forObject.entity.AnnouncementVO;


@Controller
public class AnnouncementController 
{

	private final AnnouncementService announcementService;
	
	@Autowired
	public AnnouncementController(AnnouncementService announcementService)
	{
		this.announcementService = announcementService;
	}


	
	// 글쓰기 화면
	@PostMapping(value = "/noticewrite")
	public String Write(HttpServletRequest request,
			@ModelAttribute AnnouncementDTO announcementDTO,
			@ModelAttribute AnnouncementUserDTO announcementUserDTO,
			@RequestParam("file") MultipartFile file,
			HttpSession session,
			RedirectAttributes redirectAttributes) 
	{
	
		return "announcement_write";
	}

	
}
 
