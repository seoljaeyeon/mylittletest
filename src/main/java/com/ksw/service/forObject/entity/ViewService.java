package com.ksw.service.forObject.entity;

import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.entity.ViewDTO;
import com.ksw.object.entity.View;
import com.ksw.vo.forObject.entity.ViewVO;
import com.ksw.vo.forObject.entity.ViewVO.Builder;

@Service
public class ViewService {

	
	public ViewDTO convertToDTO(View view) {
		
		ViewDTO dto = new ViewDTO();
		
		if(view == null) {	
    		System.out.println("View to ViewDTO failed. Empty ViewDTO created. View is null");
			return dto;
		}
		dto.setViewNo(view.getViewNo());
		return dto;
	}
	
	public ViewVO convertToVO(ViewDTO viewDTO) {
		if(viewDTO == null) {	
    		System.out.println("ViewDTO to ViewVO failed. Empty ViewVO created. ViewDTO is null");
			return new ViewVO.Builder().build();
		}
		return new ViewVO.Builder()
				.viewNo(viewDTO.getViewNo())
				.build();
	}
	
}
