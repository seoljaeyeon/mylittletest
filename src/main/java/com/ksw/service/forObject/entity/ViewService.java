package com.ksw.service.forObject.entity;

import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.entity.ViewDTO;
import com.ksw.object.entity.View;
import com.ksw.vo.forObject.entity.ViewVO;
import com.ksw.vo.forObject.entity.ViewVO.Builder;

@Service
public class ViewService {

	
	public ViewDTO convertToDTO(View view) {
		ViewDTO dto = new ViewDTO(
				view.getViewNo(),
				view.getCreatedAt());
		return dto;
	}
	
	public ViewVO convertToVO(ViewDTO viewDTO) {
		
		return new ViewVO.Builder()
				.viewNo(viewDTO.getViewNo())
				.createdAt(viewDTO.getCreatedAt())
				.build();
	}
	
}
