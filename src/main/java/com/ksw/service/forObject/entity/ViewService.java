package com.ksw.service.forObject.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.entity.ViewRepository;
import com.ksw.dto.forObject.entity.ViewDTO;
import com.ksw.object.entity.View;
import com.ksw.vo.forObject.entity.ViewVO;

@Service
public class ViewService {

	@Autowired
	private ViewRepository viewRepository;
	
	public View insert(View view) {
		return viewRepository.save(view);
	}
	
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
