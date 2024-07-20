package com.ksw.service.forObject.relation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.UserGoalMapper;
import com.ksw.dto.forObject.relation.UserGoalDTO;
import com.ksw.object.relation.UserGoal;
import com.ksw.service.forObject.entity.GoalService;
import com.ksw.service.forObject.entity.UserService;
import com.ksw.vo.forObject.relation.UserGoalVO;

@Service
public class UserGoalService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private GoalService goalService;
	@Autowired
	private UserGoalMapper userGoalMapper;
	
	public Integer insert(Integer userNo, Integer goalNo) {
		Integer result = 0;
		result = userGoalMapper.insert(userNo, goalNo);
		
		return result;
	}
	
	public UserGoalDTO convertToDTO(UserGoal userGoal) {
		UserGoalDTO dto = new UserGoalDTO();
		if( userGoal == null) {
			System.out.println("userGoal is null. failed converting to DTO");
			return dto;
		}
		
		dto.setCreatedAt(userGoal.getCreatedAt());
		dto.setUserDTO(userService.convertToDTO(userGoal.getUser()));
		dto.setGoalDTO(goalService.convertToDTO(userGoal.getGoal()));
		
		return dto;
	}
	
	public UserGoalVO convertToVO(UserGoalDTO userGoalDTO) {
		if (userGoalDTO == null) {
			System.out.println("userGoalDTO is null. failed converting to userGoalVO");
			return new UserGoalVO.Builder().build();
		}
		
		return new UserGoalVO.Builder()
				.userVO(userService.convertToVO(userGoalDTO.getUserDTO()))
				.goalVO(goalService.convertToVO(userGoalDTO.getGoalDTO()))
				.createdAt(userGoalDTO.getCreatedAt())
				.build();
	}

}
