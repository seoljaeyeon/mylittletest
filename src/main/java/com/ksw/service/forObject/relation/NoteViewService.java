package com.ksw.service.forObject.relation;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.NoteViewMapper;
import com.ksw.dto.forObject.relation.NoteViewDTO;
import com.ksw.object.relation.NoteView;
import com.ksw.service.forObject.entity.NoteService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.service.forObject.entity.ViewService;
import com.ksw.vo.forObject.relation.NoteViewVO;

@Service
public class NoteViewService {

	@Autowired
	private NoteService noteService;
	@Autowired
	private ViewService viewService;
	@Autowired
	private UserService userService;
	@Autowired
	private NoteViewMapper noteViewMapper;
	
	public List<NoteViewVO> GetHistory(Integer categoryNo, Integer noteNo, Integer userNo) {
		if (categoryNo == null || noteNo == null || userNo == null) {
    		System.out.println("Getting NoteViewVO History failed. One of parameters is null. Empty list returned");   	
			return Collections.singletonList(new NoteViewVO.Builder().build());
		}
		List<NoteView> list = noteViewMapper.getHistory(categoryNo, noteNo, userNo);
		return this.convertToVOList(list);
	}
	
	public NoteViewDTO convertToDTO(NoteView noteView) {
		NoteViewDTO dto = new NoteViewDTO();
		if (noteView == null) {
    		System.out.println("NoteViewDTO to NoteView failed. Empty NoteView created. NoteViewDTO is null");   	
			return dto;
		}
		
		dto.setNoteDTO(noteService.convertToDTO(noteView.getNote())); 
		dto.setViewDTO(viewService.convertToDTO(noteView.getView()));
		dto.setUserDTO(userService.convertToDTO(noteView.getUser()));  
		return dto; 
	}
	
	public NoteViewVO convertToVO(NoteViewDTO dto) {
		if (dto == null) {
    		System.out.println("NoteViewDTO to NoteViewVO failed. Empty NoteViewVO created. NoteViewDTO is null");   	

			return new NoteViewVO.Builder().build();
		}
		return new NoteViewVO.Builder()
				.noteVO(noteService.convertToVO(dto.getNoteDTO()))
				.viewVO(viewService.convertToVO(dto.getViewDTO()))
				.userVO(userService.convertToVO(dto.getUserDTO()))
				.build();
	}

	public List<NoteViewVO> convertToVOList(List<NoteView> list) {
		if (list == null) {
    		System.out.println("List<NoteView> to List<NoteViewVO> failed. Empty List<NoteViewVO> created. List<NoteView> is null");   	
			return Collections.singletonList(new NoteViewVO.Builder().build());
		}
		return list.stream()
				.map(this::convertToDTO)
				.map(this::convertToVO)
				.collect(Collectors.toList());
	}
	
}
