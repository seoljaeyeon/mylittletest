package com.ksw.dto.forObject.relation;

import java.io.Serializable;

import com.ksw.dto.forObject.entity.AlarmDTO;
import com.ksw.dto.forObject.entity.UserDTO;

public class AlarmRelationDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

    private AlarmDTO alarmDTO;
    private UserDTO receiverDTO;
    private UserDTO makerDTO;

    // 기본 생성자
    public AlarmRelationDTO() {}

	public AlarmDTO getAlarmDTO() {
		return alarmDTO;
	}

	public void setAlarmDTO(AlarmDTO alarmDTO) {
		this.alarmDTO = alarmDTO;
	}

	public UserDTO getReceiverDTO() {
		return receiverDTO;
	}

	public void setReceiverDTO(UserDTO receiverDTO) {
		this.receiverDTO = receiverDTO;
	}

	public UserDTO getMakerDTO() {
		return makerDTO;
	}

	public void setMakerDTO(UserDTO makerDTO) {
		this.makerDTO = makerDTO;
	}
}
