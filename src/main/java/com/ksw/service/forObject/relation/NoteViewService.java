package com.ksw.service.forObject.relation;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.NoteViewMapper;
import com.ksw.dto.forObject.entity.NoteDTO;
import com.ksw.dto.forObject.entity.UserDTO;
import com.ksw.dto.forObject.entity.ViewDTO;
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
	
	public List<Map<String, Object>> getRecentViewCounts(Integer userNo){
		return noteViewMapper.getRecentViewCounts(userNo);
	}
	
	public List<Map<String,Object>> getCategoryListOrderedByNoteView() {
		return noteViewMapper.getCategoryListOrderedByNoteView();
	}
	
	public List<Map<String,Object>> getTodayCategoryListByUserNo(Integer userNo) {
		return noteViewMapper.getTodayCategoryListByUserNo(userNo);
	}
	
	public List<Map<String, Object>> getNoteListByUserNo(Integer userNo){
		List<Map<String, Object>> results = noteViewMapper.getNoteListByUserNo(userNo);
		return results;
	}
	
	public Integer getPreviousNoteNo(Integer categoryNo, Integer userNo) {
		return noteViewMapper.getPreviousNoteNo(categoryNo, userNo);
	}
	
	public Boolean checkRecentViewHistory(Integer noteNo, Integer userNo) {
		System.out.println("DB최근 정보 조회: "+noteViewMapper.checkRecentViewHistory(noteNo, userNo));
		if (noteViewMapper.checkRecentViewHistory(noteNo, userNo) == 0) {
			return false;
		}
		return true;
	}
	
	public void insertRelation(ViewDTO viewDTO, NoteDTO noteDTO, UserDTO readerDTO, Timestamp timestamp ) {
		noteViewMapper.insert(viewDTO.getViewNo(), noteDTO.getNoteNo(),readerDTO.getUserNo(), timestamp);
	}

	public Integer GetTodayHistoryCount(Integer categoryNo, Integer userNo) {
		Integer result = 0;
		
		if (categoryNo == null || userNo == null) {
    		System.out.println("Getting count for today's view failed. One of parameters is null. 0 returned");   	
			return result;
		}
		result = noteViewMapper.getTodayHistory(categoryNo, userNo);
		return result;
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
	
	public List<NoteViewDTO> convertToDTOList(List<NoteView> list) {
		if (list == null ) {
    		System.out.println("List<NoteView> to List<NoteViewVO> failed. Empty List<NoteViewVO> created. List<NoteView> is null");   	
			return Collections.singletonList(new NoteViewDTO());
		}
		return list.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
}
