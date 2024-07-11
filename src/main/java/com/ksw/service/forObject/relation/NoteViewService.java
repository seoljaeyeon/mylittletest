package com.ksw.service.forObject.relation;

import java.util.List;

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
	
	public List<NoteView> GetHistory(Integer categoryNo, Integer noteNo, Integer userNo) {
		return noteViewMapper.getHistory(categoryNo, noteNo, userNo);
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
	
}
