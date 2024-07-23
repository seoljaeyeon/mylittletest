package com.ksw.service.forObject.relation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksw.dao.forObject.relation.UserGoalMapper;
import com.ksw.dto.forObject.relation.UserGoalDTO;
import com.ksw.object.entity.Goal;
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
	
	public List<Map<String, Object>> getRecentGoalsWithAnswerCounts(Integer userNo) {
		return fillMissingDates(userGoalMapper.getRecentGoalsWithAnswerCounts(userNo),userNo);
	}
	
    private List<Map<String, Object>> fillMissingDates(List<Map<String, Object>> userTarget, Integer userNo) {
        List<Map<String, Object>> completeList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        // 먼저 결과를 Map으로 변환하여 날짜를 키로 사용합니다.
        Map<String, Map<String, Object>> resultMap = new HashMap<>();
        for (Map<String, Object> entry : userTarget) {
            String date = entry.get("createdAt").toString();
            resultMap.put(date, entry);
        }
        
        // 7일 동안의 날짜를 돌면서 결과가 없으면 기본값을 채웁니다.
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            String targetDateString = formatter.format(calendar.getTime());
            if (resultMap.containsKey(targetDateString)) {
                completeList.add(resultMap.get(targetDateString));
            } else {
                Map<String, Object> defaultEntry = new HashMap<>();
                defaultEntry.put("createdAt", targetDateString);
                defaultEntry.put("answerCount", 0);
                defaultEntry.put("goalNo", null);
                defaultEntry.put("goalCount", 0);
                defaultEntry.put("userNo", userNo);
                completeList.add(defaultEntry);
            }
            calendar.add(Calendar.DATE, -1);
        }
        
        return completeList;
    }
	
	public Goal getGoalbyUserNo(Integer userNo) {
		return userGoalMapper.getGoalByUserNo(userNo);
	}
	
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
