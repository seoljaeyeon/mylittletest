package com.ksw.mylittletest;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ksw.object.entity.Goal;
import com.ksw.service.forObject.entity.GoalService;
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
        
        if (userVO != null) {
            System.out.println("Authenticated user: " + userVO);
            model.addAttribute("userVO", userVO);
        } else {
            System.out.println("No authenticated user found");
            return "redirect:/login";
        }
        return "index";    	
//	    // CSRF 토큰 확인
//	    CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//	    if (csrfToken != null) {
//	        System.out.println("CSRF Token in request: " + csrfToken.getToken());
//	    } else {
//	        System.out.println("CSRF Token not found in request");
//	    }
    }
    
    @GetMapping(value="/goalsetting", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> goalSetting(
    		Model model,
    		@RequestParam("target") Integer target
    		){
    	Map<String, Object> response = new HashMap<>();
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
			Goal goal = goalService.save(target);
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
