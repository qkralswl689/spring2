package com.javateam.SpringSessionAttributeDemo;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.javateam.SpringSessionAttributeDemo.domain.DemoVO;

import lombok.extern.log4j.Log4j2;

@Controller
@SessionAttributes("demoVO")
@Log4j2
public class SessionStatusController {
	
	@RequestMapping("/")
	public String home() {
		
		log.info("home");
		
		return "redirect:form";
	} //
	
	@RequestMapping("/sessionStatus")
	@ResponseBody
	public String sessionStatus(HttpSession session, 
								SessionStatus sessionStatus) {

		log.info("sessionStatus");
		
		log.info("sessionAttributes Session : {}", 
				session.getAttribute("demoVO"));
		
		return  "sessionAttributes Session : " + 
				session.getAttribute("demoVO");
	} //
	
	@RequestMapping("/form")
	public String form(Model model) {
		
		log.info("form");
		
		model.addAttribute("demoVO", new DemoVO());
		
		return "form";
	} //
	
	@RequestMapping(value="/action.do", method=RequestMethod.POST)
	public String action(@ModelAttribute("demoVO") DemoVO demoVO,
						HttpSession session,
						Model model) { 
		
		log.info("action.do");
		
		log.info("DemoVO : {}", demoVO.toString());
		
		model.addAttribute("demoVO", demoVO);
		
		return "sessionStatus";
	} //
	
	@RequestMapping(value="/sessionDelete.do", method=RequestMethod.GET)
	public String sessionDelete(SessionStatus sessionStatus,
								HttpSession session) { 
		
		log.info("sessionDelete.do");
		
		log.info("sess DemoVO : {}", session.getAttribute("demoVO"));
		
		// 세션 종료여부 확인
		if (sessionStatus.isComplete() == false) { // 종료가 안되었으면....
			
			// @SessionAttributes로 작성된 세션의 종료
			sessionStatus.setComplete(); // 세션 종료
			// session.invalidate(); // 주의) 기존의 세션 종료 메서드로는 비추천 !
		}
		
		return "sessionStatus";
	} //

} //