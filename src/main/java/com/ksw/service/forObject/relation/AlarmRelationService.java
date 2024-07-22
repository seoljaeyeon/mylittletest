package com.ksw.service.forObject.relation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.AlarmRelationMapper;
import com.ksw.dto.forObject.relation.AlarmRelationDTO;
import com.ksw.object.entity.User;
import com.ksw.object.relation.AlarmRelation;
import com.ksw.service.forObject.entity.AlarmService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.AlarmRelationVO;


@Service
public class AlarmRelationService {
	
	@Autowired
	private AlarmService alarmService;
	@Autowired
	private UserService userService;
	@Autowired
	private AlarmRelationMapper alarmRelationMapper;
	
	public List<List<Map<String, Object>>> getAlarmSummary(Integer userNo, Integer limit, Integer offset) {
		
		List<List<Map<String, Object>>> result = new ArrayList<>();
		List<Integer> AlarmLists = new ArrayList<>();
		
		AlarmLists = alarmRelationMapper.getAlarmListOrderByCreatedAt(userNo);
		
	    for (Integer alarmNo : AlarmLists) {
	        List<Map<String, Object>> alarmDetail = alarmRelationMapper.getAlarmDetail(alarmNo, limit, offset);
 
	        if (alarmDetail.isEmpty()) {
	        	return null;
	        }
	        
	        Integer alarmType = (Integer) alarmDetail.get(0).get("alarmType");
	        Map<String, Object> alarmNote = new HashMap<>();
	        
	        
	        if (alarmType != null && alarmType == 1) {
	            User user = userService.getUser((Integer) alarmDetail.get(0).get("makerNo"));
	            String makerName = user.getNickname();
	            alarmNote.put("alarmNote", makerName + "님이 내 문제에 좋아요를 눌렀습니다.");
	        } else if (alarmType != null && alarmType == 2) {
	            User user = userService.getUser((Integer) alarmDetail.get(0).get("makerNo"));
	            String makerName = user.getNickname();
	            alarmNote.put("alarmNote", makerName + "님이 내 문제에 댓글을 남겼습니다.");
	        }
	        
	        alarmDetail.add(alarmNote);
	        result.add(alarmDetail);
	    }
	    return result;
	}
	
	public Integer insert(Integer alarmNo, Integer receiverNo, Integer makerNo, Integer noteNo, Integer replyNo) {
		return alarmRelationMapper.insert(alarmNo, receiverNo, makerNo, noteNo, replyNo); 
	}
	
    // Entity -> DTO 변환 메소드
    public AlarmRelationDTO convertToDTO(AlarmRelation entity) {
    	AlarmRelationDTO dto = new AlarmRelationDTO();
    	if (entity == null) {
    		System.out.println("AlarmRelation to AlarmRelationDTO failed. Empty AlarmRelationDTO created. AlarmRelation is null");
    		return dto;
    	}
    	dto.setAlarmDTO(alarmService.convertToDTO(entity.getAlarm()));
    	dto.setMakerDTO(userService.convertToDTO(entity.getMaker()));
    	dto.setReceiverDTO(userService.convertToDTO(entity.getReceiver()));
    	return dto;
    }

    // DTO -> VO 변환 메소드
    public AlarmRelationVO convertToVO(AlarmRelationDTO alarmRelationDTO) {
    	if((alarmRelationDTO==null)) {
    		System.out.println("AlarmRelationDTO to AlarmRelationVO failed. Empty AlarmRelationVO created. AlarmRelationDTO is null");
    		return new AlarmRelationVO.Builder().build();
    	}
        return new AlarmRelationVO.Builder()
                .alarmVO(alarmService.convertToVO(alarmRelationDTO.getAlarmDTO()))
                .receiverVO(userService.convertToVO(alarmRelationDTO.getReceiverDTO()))
                .makerVO(userService.convertToVO(alarmRelationDTO.getMakerDTO()))
                .build();
    }
}
