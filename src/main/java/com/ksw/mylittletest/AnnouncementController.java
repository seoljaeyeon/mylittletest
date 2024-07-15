package com.ksw.mylittletest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.relation.AnnouncementUserService;
import com.ksw.service.forObject.relation.FileAnnouncementService;
import com.ksw.vo.forObject.entity.AnnouncementVO;
import com.ksw.vo.forObject.entity.FileVO;
import com.ksw.vo.forObject.relation.AnnouncementUserVO;
import com.ksw.vo.forObject.relation.FileAnnouncementVO;

@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

	
    private final AnnouncementService announcementService; // 공지사항 서비스
    
    private final AnnouncementUserService announcementUserService; // 공지사항-사용자 매핑 서비스
    
    private final FileAnnouncementService fileAnnouncementService; // 파일-공지사항 매핑 서비스
    
    private final UserService userService; // 사용자 서비스
    

    @Autowired
    public AnnouncementController(AnnouncementService announcementService, AnnouncementUserService announcementUserService,
                                  FileAnnouncementService fileAnnouncementService, UserService userService) {
        this.announcementService = announcementService;
        this.announcementUserService = announcementUserService;
        this.fileAnnouncementService = fileAnnouncementService;
        this.userService = userService;
    }

    // 글쓰기 화면 요청 처리
    @GetMapping("/write")
    public String showWriteForm() {
        return "announcement_write"; // "announcement_write" 뷰를 반환하여 글쓰기 화면을 보여줌
    }

    // 글쓰기 처리 요청 처리
    @PostMapping("/write")
    public String writeAnnouncement(HttpServletRequest request,
                                    @ModelAttribute AnnouncementDTO announcementDTO,
                                    @ModelAttribute AnnouncementUserDTO announcementUserDTO,
                                    @RequestParam("file") MultipartFile file,
                                    @RequestParam("editorContent") String editorContent,
                                    @RequestParam("reservation_time") String reservationTime,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {

        try {
            // 에디터 내용 및 예약 시간 설정
            announcementDTO.setAnnouncementContent(editorContent);
            announcementDTO.setSchedule(Timestamp.valueOf(reservationTime));

            // DTO를 VO로 변환
            AnnouncementVO announcementVO = announcementService.convertToVO(announcementDTO);

            // 공지를 데이터베이스에 저장
            Announcement announcement = announcementService.insertAnnouncement(announcementVO);

            // 파일 업로드 처리
            if (!file.isEmpty()) {
                String uploadDirectory = request.getServletContext().getRealPath("/uploads/"); // 파일 업로드 디렉토리 설정
                Files.createDirectories(Paths.get(uploadDirectory)); // 업로드 디렉토리 생성
                String filePath = uploadDirectory + file.getOriginalFilename(); // 파일 경로 설정
                file.transferTo(new File(filePath)); // 파일을 업로드 디렉토리로 저장

                // 파일 VO 생성 및 저장
                FileVO fileVO = new FileVO.Builder()
                        .filePath(filePath)
                        .fileName(file.getOriginalFilename())
                        .build();
                fileAnnouncementService.saveFile(fileVO); // 파일 서비스에 파일 저장 메서드 호출

                // 공지와 파일 매핑
                FileAnnouncementVO fileAnnouncementVO = new FileAnnouncementVO.Builder()
                        .announcementVO(announcementVO)
                        .fileVO(fileVO)
                        .build();
                fileAnnouncementService.save(fileAnnouncementVO); // 파일-공지사항 매핑 서비스에 저장 메서드 호출
            }

            // 공지와 사용자 매핑
            AnnouncementUserVO announcementUserVO = new AnnouncementUserVO.Builder()
                    .announcementVO(announcementVO)
                    .userVO(userService.convertToVO(announcementUserDTO.getUserDTO()))
                    .build();
            announcementUserService.save(announcementUserVO); // 공지사항-사용자 매핑 서비스에 저장 메서드 호출

            redirectAttributes.addFlashAttribute("message", "공지사항이 등록되었습니다!"); // 리다s이렉트 시 메시지 전달

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "파일 업로드에 실패했습니다."); // 예외 발생 시 메시지 전달
            e.printStackTrace(); // 예외 출력
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "공지사항 등록실패했습니다."); // 예외 발생 시 메시지 전달
            e.printStackTrace(); // 예외 출력
        }

        return "redirect:/announcements/list"; // 공지사항 목록 페이지로 리다이렉트
    }
}
