package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.FileAnnouncementDTO;
import com.ksw.object.relation.FileAnnouncement;
import com.ksw.service.forObject.entity.AnnouncementService;
import com.ksw.service.forObject.entity.FileService;
import com.ksw.vo.forObject.relation.FileAnnouncementVO;

@Service
public class FileAnnouncementService {

	@Autowired
	private AnnouncementService announcementService;
	@Autowired
	private FileService fileService;
	
    // Entity -> DTO 변환 메소드
    public FileAnnouncementDTO convertToDTO(FileAnnouncement fileAnnouncementEntity) {
    	FileAnnouncementDTO dto = new FileAnnouncementDTO();
    	dto.setAnnouncementDTO(announcementService.convertToDTO(fileAnnouncementEntity.getAnnouncement()));
    	dto.setFileDTO(fileService.convertToDTO(fileAnnouncementEntity.getFile()));
        return dto;
    }

    // DTO -> VO 변환 메소드
    public FileAnnouncementVO convertToVO(FileAnnouncementDTO fileAnnouncementDTO) {
        return new FileAnnouncementVO.Builder()
                .announcementVO(announcementService.convertToVO(fileAnnouncementDTO.getAnnouncementDTO()))
                .fileVO(fileService.convertToVO(fileAnnouncementDTO.getFileDTO()))
                .build();
    }
}
