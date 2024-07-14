package com.ksw.service.forObject.entity;

import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.entity.AlarmDTO;
import com.ksw.object.entity.Alarm;
import com.ksw.vo.forObject.entity.AlarmVO;

@Service
public class AlarmService {

    // Entity -> DTO 변환 메소드
    public AlarmDTO convertToDTO(Alarm alarm) {
    	AlarmDTO dto = new AlarmDTO();
    	if (alarm == null) {
    		System.out.println("Alarm->AlarmDTO failed. Empty AlarmDTO created. Alarm is null");
            return dto;
    	}
    	dto.setAlarmNo(alarm.getAlarmNo());
    	dto.setAlarmNote(alarm.getAlarmNote());
    	dto.setAlarmType(alarm.getAlarmType());
    	dto.setCreatedAt(alarm.getCreatedAt());
    	dto.setIsRead(alarm.getIsRead());
    	return dto;
    }

    // DTO -> VO 변환 메소드
    public AlarmVO convertToVO(AlarmDTO alarmDTO) {
    	if (alarmDTO == null) {
    		System.out.println("AlarmDTO to AlarmVO failed. Empty AlarmVO created. AlarmDTO is null");
            return new AlarmVO.Builder().build();
    	}
        return new AlarmVO.Builder()
                .alarmNo(alarmDTO.getAlarmNo())
                .alarmNote(alarmDTO.getAlarmNote())
                .alarmType(alarmDTO.getAlarmType())
                .isRead(alarmDTO.getIsRead())
                .createdAt(alarmDTO.getCreatedAt())
                .build();
    }
}