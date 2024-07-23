package com.ksw.mylittletest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksw.object.entity.Goal;
import com.ksw.service.forObject.entity.GoalService;
import com.ksw.service.forObject.relation.NoteViewService;
import com.ksw.service.forObject.relation.UserGoalService;
import com.ksw.service.function.AuthService;
import com.ksw.vo.forObject.entity.UserVO;

@Controller
@RequestMapping
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private AuthService	authService;
	@Autowired
	private UserGoalService userGoalService;
	@Autowired
	private GoalService goalService;
	@Autowired
	private NoteViewService noteViewService;
	
	
	
	@GetMapping("/")
	public String toHome() {
		return "redirect:/index";
	}
	
    @GetMapping("/index")
	public String home(
			HttpSession session, 
			Model model, 
			HttpServletRequest request) {
    	
    	UserVO userVO = authService.getUserVO();
    	
    	// 최근 달성률 / 오늘달성률 / 오늘의 목표
    	
        if (userVO != null) {
            System.out.println("Authenticated user: " + userVO);
            model.addAttribute("userVO", userVO);
        } else {
            System.out.println("No authenticated user found");
            return "redirect:/login";
        }
        
        List<Map<String, Object>> userTarget = new ArrayList<>();
        userTarget = userGoalService.getRecentGoalsWithAnswerCounts(userVO.getUserNo());
        
        model.addAttribute("GoalDetails", userTarget);
        
        return "index";    	
    }
    
    @PostMapping(value="/goalsetting", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> goalSetting(
    		Model model,
    		@RequestBody Map<String, Object> requestBody
    		){
    	Map<String, Object> response = new HashMap<>();
    	if (requestBody.isEmpty()) {
    		response.put("status", "failed");
    		return response;
    	}
    	
        Integer target = Integer.valueOf((String) requestBody.get("target")) ;

    	UserVO userVO = authService.getUserVO();
		if (userVO == null || userVO.getUserNo() == null) {
			response.put("status", "loing_needed");
			response.put("url", "/mylittletest/login");
			return response;
		}  
		
		if (target == null) {
			response.put("status", "parameter_null");
			return response;
		}
		
		model.addAttribute("userVO", userVO);
		try {
			Goal goal = userGoalService.getGoalbyUserNo(userVO.getUserNo()); 
			
			if(goal != null) {
				response.put("status", "exist");
				response.put("result", "오늘은 이미 목표를 설정했습니다.");
				return response;
			}
			goal = goalService.save(target);
			Integer result = userGoalService.insert(userVO.getUserNo(), goal.getGoalNo());
			response.put("status", "success");
			response.put("result", target);
			return response;
		} catch(Exception e) {
			e.printStackTrace();
			response.put("status", "failed");
			return response;
		}
    }
}
