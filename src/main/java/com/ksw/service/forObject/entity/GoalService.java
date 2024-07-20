package com.ksw.service.forObject.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.entity.GoalRepository;
import com.ksw.dto.forObject.entity.GoalDTO;
import com.ksw.object.entity.Goal;
import com.ksw.vo.forObject.entity.GoalVO;

@Service
public class GoalService {

	@Autowired
	private GoalRepository goalRepository;

	public Goal save(Integer target) {
		Goal goal = new Goal();
		goal.setGoalCount(target);
		return goalRepository.save(goal);
	}
	
	public GoalDTO convertToDTO(Goal goal) {
		GoalDTO dto = new GoalDTO();
		if(goal == null) {
			System.out.println("goal is null. failed to convert to DTO.");
			return dto;
		}
		dto.setGoalNo(goal.getGoalNo());
		dto.setGoalCount(goal.getGoalCount());
		return dto;
	}
	
	public GoalVO convertToVO(GoalDTO goalDTO) {
		if(goalDTO == null) {
			System.out.println("goalDTO is null. converting to GoalVO was failed");
			return new GoalVO.Builder().build();
		}
		return new GoalVO.Builder()
				.goalNo(goalDTO.getGoalNo())
				.goalCount(goalDTO.getGoalCount())
				.build();
	}
}
