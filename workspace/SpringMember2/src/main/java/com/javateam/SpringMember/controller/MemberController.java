package com.javateam.SpringMember.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
		log.info("회원조회(보기)폼");

		MemberVO memberVO = memberService.getMember(memberId);
		log.info("회원 정보 : " + memberVO);
		
		log.info("회원 정보 : VO -> DTO 변환");
		MemberDTO member = new MemberDTO(memberVO);
		
		model.addAttribute("member", member);
		model.addAttribute("birth", memberVO.getMemberBirth());
		
		return "/member/member_view";
	}	
	
	@RequestMapping("member_update.do")
	public String memberUpdateForm(@RequestParam("memberId") String memberId,
					Model model) {
	
		log.info("회원수정폼");
		
		MemberVO memberVO = memberService.getMember(memberId);
		
		log.info("회원 정보 : VO -> DTO 변환");
		MemberDTO member = new MemberDTO(memberVO);		
		
		model.addAttribute("member", member);
		model.addAttribute("birth", memberVO.getMemberBirth());
		
		return "/member/member_update";
	}

	@RequestMapping(value="update_proc.do", method=RequestMethod.POST)
	public String updateProc(MemberDTO newMemberDTO,
							@RequestParam("memberPassword1") String newMemberPassword) {
		
		log.info("회원수정 처리");
		
		log.info("#### 회원정보 : " + newMemberDTO);
			
		MemberDTO legacyMember = new MemberDTO(memberService.getMember(newMemberDTO.getMemberId()));
		
		// 기존 회원정보의 가입일 정보 입력
		newMemberDTO.setMemberBirth(new SimpleDateFormat("yyyy-MM-dd").format(legacyMember.getMemberJoinDate().getTime()));
		
		log.info("##########################################");
		log.info("기존 회원정보 : " + legacyMember);
		log.info("수정 회원정보 : " + newMemberDTO);
		log.info("신규 패쓰워드 : " + newMemberPassword);
				
		MemberVO memberVO = new MemberVO(newMemberDTO);
		
		// 패쓰워드 갱신 버그 패치
		String newMemberPw = newMemberPassword.trim().equals("") ? memberVO.getMemberPassword()
						   : newMemberPassword;
		memberVO.setMemberPassword(newMemberPw);
		
		log.info("저장 회원 정보 : " + memberVO);		
		
		memberService.updateMember(memberVO);
		
		return "redirect:/welcome.do";
	}
	
	@RequestMapping("member_delete.do")
	public String memberDeleteForm() {
	
		log.info("회원삭제폼");
		return "/member/member_delete";
	}
	
	@RequestMapping(value="delete_proc.do", method=RequestMethod.POST)
	public String memberDeleteProc(@RequestParam("memberId") String memberId,
					Model model) {
	
		log.info("회원삭제 처리 : " + memberService.isMember(memberId));
		
		if (memberService.isMember(memberId)==true) {
			memberService.deleteMember(memberId);
		} else {
			model.addAttribute("db_error", "회원 정보가 존재하지 않습니다.");
			return "/error/db-err";
		}
		
		return "redirect:/login.do";
	}
	
}