package com.javateam.SpringMember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.SpringMember.domain.MemberDTO;
import com.javateam.SpringMember.domain.MemberVO;
import com.javateam.SpringMember.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
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
	
	@RequestMapping("member_view.do")
	public String memberViewForm(@RequestParam("memberId") String memberId,
					Model model) {
	
		log.debug("회원조회(보기)폼");

		MemberVO memberVO = memberService.getMember(memberId);
		log.info("회원 정보 : " + memberVO);
		
		log.info("회원 정보 : VO -> DTO 변환");
		MemberDTO member = new MemberDTO(memberVO);
		
		model.addAttribute("member", member);
		model.addAttribute("birth", memberVO.getMemberBirth());
		
		return "/member/member_view";
	}	
	
}