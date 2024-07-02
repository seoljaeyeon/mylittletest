package com.ksw.service.object;

import com.ksw.dto.forObject.FileUserDTO;
import com.ksw.object.entity.mybatis.FileUser;
import com.ksw.object.vo.FileUserVO;
import org.springframework.stereotype.Service;

@Service
public class FileUserService {

    // Entity -> DTO 변환 메소드
    public FileUserDTO convertToDTO(FileUser fileUserEntity) {
        return new FileUserDTO.Builder()
                .userNo(fileUserEntity.getUserNo())
                .fileNo(fileUserEntity.getFileNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public FileUserVO convertToVO(FileUserDTO fileUserDTO) {
        return new FileUserVO.Builder()
                .userNo(fileUserDTO.getUserNo())
                .fileNo(fileUserDTO.getFileNo())
                .build();
    }
}
