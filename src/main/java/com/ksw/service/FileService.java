package com.ksw.service;

import com.ksw.dto.forObject.FileDTO;
import com.ksw.object.entity.jpa.File;
import com.ksw.object.vo.FileVO;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    // Entity -> DTO 변환 메소드
    public FileDTO convertToDTO(File file) {
        return new FileDTO.Builder()
                .fileNo(file.getFileNo())
                .savedName(file.getSavedName())
                .uploadName(file.getUploadName())
                .createdAt(file.getCreatedAt())
                .build();
    }

    // DTO -> VO 변환 메소드
    public FileVO convertToVO(FileDTO fileDTO) {
        return new FileVO.Builder()
                .fileNo(fileDTO.getFileNo())
                .savedName(fileDTO.getSavedName())
                .uploadName(fileDTO.getUploadName())
                .createdAt(fileDTO.getCreatedAt())
                .build();
    }
}
