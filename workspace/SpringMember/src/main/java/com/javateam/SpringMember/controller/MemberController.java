package com.javateam.SpringMember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javateam.SpringMember.domain.MemberDTO;
import com.javateam.SpringMember.domain.MemberVO;
import com.javateam.SpringMember.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("member_join.do")
	public String memberJoinForm() {
	
		log.info("회원가입폼");
		return "/member/member_join";
	}
	
	@RequestMapping("join_proc.do")
	public String joinProc(@ModelAttribute("memberDTO") MemberDTO memberDTO, Model model) {
		
		log.info("회원가입 처리");
		String path = "/member/join_action";
		
		log.info("회원정보 : " + memberDTO);
		
		MemberVO memberVO = new MemberVO(memberDTO);
		log.info("저장 회원 정보 : " + memberVO);
		
		try {
			
			memberService.insertMember(memberVO);
			
		} catch (Exception e) {
			log.error("회원 가입 db 에러 발생 : " + e.getMessage());
			model.addAttribute("db_error", e.getMessage());
			path = "/error/db-err";
		} //
		
		return path;
	}
	
}