package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.AnnouncementUserDTO;
import com.ksw.object.relation.AnnouncementUser;
import com.ksw.service.forObject.entity.AnnouncementService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.AnnouncementUserVO;

@Service
public class AnnouncementUserService {

	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private UserService userService;
	
    // Entity -> DTO 변환 메소드
    public AnnouncementUserDTO convertToDTO(AnnouncementUser entity) {
    	AnnouncementUserDTO dto = new AnnouncementUserDTO();
    	if (entity==null) {	
    		System.out.println("AnnouncementUser to AnnouncementUserDTO failed. Empty AnnouncementUserDTO created. AnnouncementUser is null");
    		return dto;
    	}
        dto.setAnnouncementDTO(announcementService.convertToDTO(entity.getAnnouncement()));
        dto.setUserDTO(userService.convertToDTO(entity.getUser()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public AnnouncementUserVO convertToVO(AnnouncementUserDTO announcementUserDTO) {
    	if (announcementUserDTO == null) {
    		System.out.println("AnnouncementUserDTO to AnnouncementUserVO failed. Empty AnnouncementUserVO created. AnnouncementUserDTO is null");
    		return new AnnouncementUserVO.Builder().build();
    	}
        return new AnnouncementUserVO.Builder()
                .announcementVO(announcementService.convertToVO(announcementUserDTO.getAnnouncementDTO()))
                .userVO(userService.convertToVO(announcementUserDTO.getUserDTO()))
                .build();
    }
}
