package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.relation.AlarmRelationDTO;
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
