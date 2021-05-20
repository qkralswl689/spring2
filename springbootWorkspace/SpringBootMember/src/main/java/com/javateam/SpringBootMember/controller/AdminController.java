package com.javateam.SpringBootMember.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.SpringBootMember.domain.CustomUser;
import com.javateam.SpringBootMember.domain.MemberDTO;
import com.javateam.SpringBootMember.domain.MemberVO;
import com.javateam.SpringBootMember.domain.PageVO;
import com.javateam.SpringBootMember.service.MemberService;

import lombok.extern.java.Log;
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
				
		int membersNum = memberService.getCountAllMembers();
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
			// members.add(new MemberDTO(memberVO)); 
			members.add(new MemberDTO(memberVO));
		} //		
		
		// 전송 인자  
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("members", members);
		model.addAttribute("limit", limit);
		
		return "/admin/view_all";
	} //
	
	@RequestMapping("search_proc.do")
    public String searchProc(@RequestParam(value="search_word", defaultValue="") String searchWord,
                             @RequestParam(value="search_kind", defaultValue="") String searchKind,
                             @RequestParam(value="page", defaultValue="1") int page,
                             Model model) {
       
        log.debug("회원 정보 검색");
        
        String returnPath = "";
       
        // 검색 구분 및 검색어 점검
        if (searchWord.trim().equals("")) {
           
            log.debug("검색어 미입력");
            model.addAttribute("msg", "검색어를 입력하십시오.");
            model.addAttribute("move_page", "/admin/members_all_view.do");
            returnPath = "/error/result";
           
        } else {
 
            boolean isLike = false; // 유사 검색 여부
 
            String fld = searchKind.contentEquals("아이디") ? "member_id" :
                         searchKind.contentEquals("별명") ? "member_nickname" :
                         searchKind.contentEquals("이름") ? "member_name" :
                         searchKind.contentEquals("기본 주소") ? "member_address" :
                         searchKind.contentEquals("상세 주소") ? "member_address" : "member_id";  
           
            // 유사 검색 설정
            if (searchKind.contentEquals("이름") ||
            	searchKind.contentEquals("기본 주소") || 
        		searchKind.contentEquals("상세 주소")) {
                isLike = true;
            }  
                           
            int limit = 10; // 한 페이지에 표현될 수 있는 인원수
           
            log.debug("#### 검색 필드 : "+fld);
            log.debug("#### 검색어 : "+searchWord);
           
            List<MemberVO> memberVOList = memberService.getMembersByFieldAndPaging(fld, searchWord, isLike, page, limit);
           
            int membersNum = memberService.getCountMembersByFieldAndPaging(fld, searchWord, isLike, limit);
            int listCount = memberVOList.size();
           
            // 총 페이지 수
            int maxPage = (int)((double)membersNum/10+0.95); //0.95를 더해서 올림 처리
            // 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21,...)
            int startPage = (((int) ((double)page/10 + 0.9)) - 1) * 10 + 1;
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
           
            //  검색 페이징 인자 : 검색 여부/검색필드/검색어 전송
            model.addAttribute("searchYN", "search");
            model.addAttribute("searchKind", searchKind);
            model.addAttribute("searchWord", searchWord);
        }  
           
        return "/admin/view_all";
    } //
	
}