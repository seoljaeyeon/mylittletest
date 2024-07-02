package com.ksw.service.object;

import org.springframework.stereotype.Service;

import com.ksw.dto.forObject.AlarmRelationDTO;
import com.ksw.object.entity.mybatis.AlarmRelation;
import com.ksw.object.vo.AlarmRelationVO;

@Service
public class AlarmRelationService {
    // Entity -> DTO 변환 메소드
    public AlarmRelationDTO convertToDTO(AlarmRelation alarmRelation) {
        return new AlarmRelationDTO.Builder()
                .alarmNo(alarmRelation.getAlarmNo())
                .userNo(alarmRelation.getUserNo())
                .makerNo(alarmRelation.getMakerNo())
                .build();
    }

    // DTO -> VO 변환 메소드
    public AlarmRelationVO convertToVO(AlarmRelationDTO alarmRelationDTO) {
        return new AlarmRelationVO.Builder()
                .alarmNo(alarmRelationDTO.getAlarmNo())
                .userNo(alarmRelationDTO.getUserNo())
                .makerNo(alarmRelationDTO.getMakerNo())
                .build();
    }
}
