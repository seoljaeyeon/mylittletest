package com.ksw.mylittletest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksw.dto.function.JoinDTO;
import com.ksw.service.function.JoinService;

@Controller
public class JoinController {
	
	@Autowired
	private JoinService joinService;
	
    @GetMapping("/join")
    public String showJoinPage() {
        return "join";
    }
	
	@GetMapping("/joincomplete")
	public String tojoincomplate() {
		return "joincomplete";
	}
	
    @PostMapping("/join")
    public String join(@ModelAttribute JoinDTO joinDTO, RedirectAttributes redirectAttributes) {
    	String result = "";
        result = joinService.join(joinDTO);
        return "redirect:/joincomplete";
    }
    
    
    // 메일 전송 구현 틀
    @PostMapping("/sendMail")
    @ResponseBody
    public String sendMail(
    		@RequestParam("email") String email) {
    	return "success"; 
    }
    
	
}
