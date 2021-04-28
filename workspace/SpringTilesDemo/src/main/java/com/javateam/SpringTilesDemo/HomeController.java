package com.javateam.SpringTilesDemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		log.info("home");
		
		model.addAttribute("pageHeader", "main header");
		
		return "/main/home";
	}
	
	@RequestMapping(value = "/link1.do", method = RequestMethod.GET)
	public String home2(Model model) {

		log.info("link1.do");
		
		model.addAttribute("pageHeader", "link1 header");
		
		return "/main/link1";
	}
	
	
	@RequestMapping(value = "/anotherLayout.do", method = RequestMethod.GET)
	public String anotherLyaout(Model model) {

		log.info("anotherLayout.do");
		
		model.addAttribute("pageHeader", "another layout header");
		
		return "another_layout"; // tile의 다른 레이아웃으로 교체
	}
}
