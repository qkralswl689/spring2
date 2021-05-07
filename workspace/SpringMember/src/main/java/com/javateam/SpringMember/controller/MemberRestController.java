package com.javateam.SpringMember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javateam.SpringMember.service.MemberService;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RestController
@Log4j2
@RequestMapping("member")
public class MemberRestController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "idCheck.do", 
					produces = "text/plain; charset=UTF-8")
	public String isMember(@RequestParam("memberId") String memberId) {
		
		log.debug("### MemberRestController : idCheck.do ###");
		return memberService.isMember(memberId.trim()) == true ? 
			   "아이디가 이미 존재합니다." : "사용할 수 있는 아이디입니다.";
	} //
	
	@RequestMapping(value = "emailCheck.do", 
					produces = "text/plain; charset=UTF-8")
	public String isEnableEmail(@RequestParam("memberEmail") String memberEmail) {
	
		log.debug("### MemberRestController : emailCheck.do ###");
		return memberService.isEnableEmail(memberEmail.trim()) == true ? 
			   "중복 이메일이 이미 존재합니다." : "사용할 수 있는 이메일입니다.";
	} //
	
	@RequestMapping(value = "phoneCheck.do", 
					produces = "text/plain; charset=UTF-8")
	public String isEnablePhone(@RequestParam("memberPhone") String memberPhone) {
	
		log.debug("### MemberRestController : phoneCheck.do ###");
		return memberService.isEnablePhone(memberPhone.trim()) == true ? 
			   "중복 연락처가 이미 존재합니다." : "사용할 수 있는 연락처입니다.";
	} //
	
}