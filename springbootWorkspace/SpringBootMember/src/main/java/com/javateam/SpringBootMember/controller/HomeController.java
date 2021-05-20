package com.javateam.SpringBootMember.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@Log
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		
		log.info("home");
		return "redirect:/login.do";
	}

} //