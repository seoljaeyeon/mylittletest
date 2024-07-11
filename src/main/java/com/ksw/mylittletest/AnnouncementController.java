package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

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
import com.ksw.dto.forObject.relation.AnnouncementUserDTO;
import com.ksw.service.forObject.entity.AnnouncementService;
import com.ksw.service.forObject.relation.AnnouncementUserService;
import com.ksw.vo.forObject.entity.AnnouncementVO;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementUserService announcementUserService;
    private final AnnouncementService announcementService;

    @Autowired
    public AnnouncementController(AnnouncementService announcementService, AnnouncementUserService announcementUserService) {
        this.announcementService = announcementService;
        this.announcementUserService = announcementUserService;
    }

    // 글쓰기 화면
    @GetMapping("/write")
    public String showWriteForm() {
        return "announcement_write";
    }

    // 글쓰기 처리
    @PostMapping("/write")
    public String writeAnnouncement(HttpServletRequest request,
                                    @ModelAttribute AnnouncementDTO announcementDTO,
                                    @ModelAttribute AnnouncementUserDTO announcementUserDTO,
                                    @RequestParam("file") MultipartFile file,
                                    @RequestParam("editorContent") String editorContent,
                                    @RequestParam("reservation_time") String reservationTime,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String uploadDir = request.getServletContext().getRealPath("/uploads/");
            try {
                File uploadFile = new File(uploadDir, fileName);
                file.transferTo(uploadFile);
                // 파일 경로를 AnnouncementDTO에 설정 (필요한 경우)
                announcementDTO.setFilePath(uploadFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("message", "File upload failed: " + e.getMessage());
                return "redirect:/announcements/write";
            }
        }

        // 현재 시간 설정
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        announcementDTO.setCreatedAt(currentTimestamp);
        announcementDTO.setUpdatedAt(currentTimestamp);
        announcementDTO.setContent(editorContent);
        announcementDTO.setReservationTime(Timestamp.valueOf(reservationTime));

        try {
            AnnouncementDTO writeAnnouncement = announcementService.writeannouncement(announcementDTO);
            redirectAttributes.addFlashAttribute("message", "Announcement created successfully with ID: " + createdAnnouncement.getAnnouncementNo());
            return "redirect:/announcements/list"; // 공지사항 목록 페이지로 리다이렉트
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error creating announcement: " + e.getMessage());
            return "redirect:/announcements/write";
        }
    }
}
