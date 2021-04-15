package com.javateam.springFormJPAValidation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javateam.springFormJPAValidation.vo.MemberValid;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	
	@RequestMapping("/")
	public String startup() {
		
		log.info("startup !");
		
		return "redirect:form.do";
	}
	
	@RequestMapping("/form.do")
	public String form(Model model) {
	
		log.info("form.do");
		
		if(!model.containsAttribute("memberValid")) 
			model.addAttribute("memberValid", new MemberValid());
		
		return "form";
	} //
	
	@RequestMapping(value="/formActionValid.do",
					method=RequestMethod.POST) 
										// request 없이 ModelAttribute 으로 사용 
										// 모델 : DTO	
									// @Valid : 폼 점검을 해준다 : VO 통채로 폼점검
	public String formActionValid(@Valid @ModelAttribute("memberValid") MemberValid memberValid,
								  BindingResult result, // 에러처리 : BindingResult
								  RedirectAttributes ra, 
								  Model model) {
		
		log.info("Validation action");
		
		if (result.hasErrors()) {
			
			log.error("error !");
			log.error("form error redirect page !");
			
			// 날짜 관련 오류 
			log.error("날짜 : "+result.getFieldValue("birthday"));
			log.error("날짜 오류 : "+result.getFieldErrors("birthday"));
			
			// 이메일 관련 오류
			log.error("email : "+result.getFieldErrors("email"));
			
			// 생일 필드 에러 메시지
			if (result.hasFieldErrors("birthday")) {
				
				log.error("생일 정보 에러 있음 !!");
				
				List<FieldError> birthdayErrList = result.getFieldErrors("birthday");
				
				log.error("생일 필드 기정(default) 오류값 : " 
						 + birthdayErrList.get(birthdayErrList.size()-1).getDefaultMessage());
				
				String msg = birthdayErrList.get(birthdayErrList.size()-1).getDefaultMessage();
						
				if (msg.trim().equals("반드시 값이 있어야 합니다.")) {
					ra.addFlashAttribute("birthday_error", 
										  birthdayErrList.get(birthdayErrList.size()-1).getDefaultMessage());	
				} else {
					ra.addFlashAttribute("birthday_error", "잘못된 생년월일 형식입니다. 다시 입력하십시오");
				} //
					
			}
			
			// 오류값 객체 전송
			// forword 와 같이 인자가 넘어간다
			ra.addFlashAttribute("org.springframework.validation.BindingResult.memberValid", 
							     result);
			
			// VO 입력값 전송
			ra.addFlashAttribute("memberValid", memberValid);
			
			return "redirect:/form.do";
		}
		
		// Service/DAO 저장 (생략)
		
		return "success";
	} //
		
}
