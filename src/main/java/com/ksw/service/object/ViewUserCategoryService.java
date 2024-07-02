package com.ksw.service.object;

import com.ksw.dto.forObject.ViewUserCategoryDTO;
import com.ksw.object.entity.mybatis.ViewUserCategory;
import com.ksw.object.vo.ViewUserCategoryVO;
import org.springframework.stereotype.Service;

@Service
public class ViewUserCategoryService {

    // Entity -> DTO 변환 메소드
    public ViewUserCategoryDTO convertToDTO(ViewUserCategory viewUserCategoryEntity) {
        return new ViewUserCategoryDTO.Builder()
                .userNo(viewUserCategoryEntity.getUserNo())
                .categoryNo(viewUserCategoryEntity.getCategoryNo())
                .categoryViewNo(viewUserCategoryEntity.getCategoryViewNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public ViewUserCategoryVO convertToVO(ViewUserCategoryDTO viewUserCategoryDTO) {
        return new ViewUserCategoryVO.Builder()
                .userNo(viewUserCategoryDTO.getUserNo())
                .categoryNo(viewUserCategoryDTO.getCategoryNo())
                .categoryViewNo(viewUserCategoryDTO.getCategoryViewNo())
                .build();
    }
}
