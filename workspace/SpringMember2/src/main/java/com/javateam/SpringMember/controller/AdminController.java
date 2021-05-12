package com.javateam.SpringMember.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.SpringMember.domain.CustomUser;
import com.javateam.SpringMember.domain.MemberDTO;
import com.javateam.SpringMember.domain.MemberVO;
import com.javateam.SpringMember.domain.PageVO;
import com.javateam.SpringMember.service.MemberService;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	private MemberService memberService;
	
	// 추가
	@RequestMapping("/")
	public String adminHome(ModelMap model) {
		
		log.info("### admin home ###");
		
		Object principal = SecurityContextHolder.getContext()
												.getAuthentication()
												.getPrincipal();
		
		CustomUser user = null;
		
		if (principal instanceof CustomUser) {
			user = ((CustomUser)principal);
		}
		
		log.info("user : " +user);
		
		// String name = user.getUsername();
		//model.addAttribute("username", name);
		//model.addAttribute("message", "관리자 페이지에 들어오셨습니다.");
		
		return "redirect:/admin/members_all_view.do";
	}
	
	@RequestMapping("members_all_view.do")
	public String viewAll(@RequestParam(value="page", defaultValue="1") int page, 
				Model model) {
		
		log.debug("전체 회원 조회");

		int limit = 10; // 한 페이지에 표현될 수 있는 인원수 
		List<MemberVO> memberVOList = memberService.getMembersByPaging(page, limit);
				
		int membersNum = memberService.getAllMembers().size();
		int listCount = memberVOList.size();
		
		// 총 페이지 수
   		int maxPage = (int)((double)membersNum/10+0.95); //0.95를 더해서 올림 처리
		// 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21,...)
   		int startPage = (((int)((double)page/10 + 0.9)) - 1) * 10 + 1;
		// 현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30, ...)
   	    int endPage = startPage + 10 - 1;
   		
   		log.debug("startPage : "+startPage);
   		log.debug("endPage : "+endPage);
   	    
   	    if (endPage > maxPage) endPage = maxPage;
   	    
   	    PageVO pageVO = new PageVO();
		pageVO.setMaxPage(maxPage);
		pageVO.setPage(page);
		pageVO.setStartPage(startPage);
		pageVO.setEndPage(endPage);
		pageVO.setListCount(listCount);
		
		// 치환 : List<MemberVO> -> List<MemberDTO>
		List<MemberDTO> members = new ArrayList<>();
		
		for (MemberVO memberVO : memberVOList) {
			members.add(new MemberDTO(memberVO)); 
		} //		
		
		// 전송 인자  
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("members", members);
		model.addAttribute("limit", limit);
		
		return "/admin/view_all";
	} //
	
}