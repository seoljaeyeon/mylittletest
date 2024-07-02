package com.ksw.service.object;

import com.ksw.dto.forObject.AlarmDTO;
import com.ksw.object.entity.jpa.Alarm;
import com.ksw.object.vo.AlarmVO;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {

    // Entity -> DTO 변환 메소드
    public AlarmDTO convertToDTO(Alarm alarm) {
        return new AlarmDTO.Builder()
                .alarmNo(alarm.getAlarmNo())
                .alarmNote(alarm.getAlarmNote())
                .alarmType(alarm.getAlarmType())
                .isRead(alarm.getIsRead())
                .createdAt(alarm.getCreatedAt())
                .build();
    }

    // DTO -> VO 변환 메소드
    public AlarmVO convertToVO(AlarmDTO alarmDTO) {
        return new AlarmVO.Builder()
                .alarmNo(alarmDTO.getAlarmNo())
                .alarmNote(alarmDTO.getAlarmNote())
                .alarmType(alarmDTO.getAlarmType())
                .isRead(alarmDTO.getIsRead())
                .createdAt(alarmDTO.getCreatedAt())
                .build();
    }
}