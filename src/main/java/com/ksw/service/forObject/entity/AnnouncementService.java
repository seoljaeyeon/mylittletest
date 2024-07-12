package com.ksw.service.forObject.entity;

import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.entity.AnnouncementDTO;
import com.ksw.object.entity.Announcement;
import com.ksw.vo.forObject.entity.AnnouncementVO;

@Service
public class AnnouncementService {

    // Entity -> DTO 변환 메소드
    public AnnouncementDTO convertToDTO(Announcement announcement) {
    	AnnouncementDTO dto = new AnnouncementDTO();
    	if (announcement == null) {
    		System.out.println("Announcement to AnnouncementDTO failed. Empty AnnouncementDTO created. Announcement is null");
            return dto;
    	}
    	dto.setAnnouncementContent(announcement.getAnnouncementContent());
    	dto.setAnnouncementNo(announcement.getAnnouncementNo());
    	dto.setAnnouncementTitle(announcement.getAnnouncementTitle());
    	dto.setCreatedAt(announcement.getCreatedAt());
    	dto.setIsActive(announcement.getIsActive());
    	dto.setSchedule(announcement.getSchedule());
    	dto.setUpdatedAt(announcement.getUpdatedAt());
        return dto;
    }

    // DTO -> VO 변환 메소드
    public AnnouncementVO convertToVO(AnnouncementDTO announcementDTO) {
    	if (announcementDTO == null) {
    		System.out.println("AnnouncementDTO to AnnouncementVO failed. Empty AnnouncementVO created. AnnouncementDTO is null");
            return new AnnouncementVO.Builder().build();
    	}
        return new AnnouncementVO.Builder()
                .announcementNo(announcementDTO.getAnnouncementNo())
                .announcementTitle(announcementDTO.getAnnouncementTitle())
                .announcementContent(announcementDTO.getAnnouncementContent())
                .schedule(announcementDTO.getSchedule())
                .isActive(announcementDTO.getIsActive())
                .createdAt(announcementDTO.getCreatedAt())
                .updatedAt(announcementDTO.getUpdatedAt())
                .build();
    }
}
