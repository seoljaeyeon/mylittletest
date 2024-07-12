package com.ksw.service.forObject.relation;

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
		List<NoteView> list = noteViewMapper.getHistory(categoryNo, noteNo, userNo);
		return this.convertToVOList(list);
	}
	
	public NoteViewDTO convertToDTO(NoteView noteView) {
		NoteViewDTO dto = new NoteViewDTO(
				noteService.convertToDTO(noteView.getNote()),
				viewService.convertToDTO(noteView.getView()),
				userService.convertToDTO(noteView.getUser()));
		return dto; 
	}
	
	public NoteViewVO convertToVO(NoteViewDTO dto) {
		return new NoteViewVO.Builder()
				.noteVO(noteService.convertToVO(dto.getNoteDTO()))
				.viewVO(viewService.convertToVO(dto.getViewDTO()))
				.userVO(userService.convertToVO(dto.getUserDTO()))
				.build();
	}

	public List<NoteViewVO> convertToVOList(List<NoteView> list) {
		return list.stream()
				.map(this::convertToDTO)
				.map(this::convertToVO)
				.collect(Collectors.toList());
	}
	
}
