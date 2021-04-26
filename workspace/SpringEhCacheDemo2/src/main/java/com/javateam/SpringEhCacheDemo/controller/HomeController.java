package com.javateam.SpringEhCacheDemo.controller;

import javax.inject.Inject;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javateam.SpringEhCacheDemo.service.EmployeesService;

import lombok.extern.slf4j.Slf4j;

@Controller
@EnableCaching
@Slf4j
public class HomeController {

	@Inject
	private EmployeesService svc;

	@RequestMapping(value="/", method=RequestMethod.GET) 
	public String home(Model model) {

		log.info("home");

		model.addAttribute("members", svc.getAll());
		
		return "home";
	} //
	
	
	@RequestMapping(value="/update", method=RequestMethod.GET) 
	@ResponseBody
	public String update(Model model) {

		log.info("update");
		boolean flag = svc.updateCommissionPct(0.20f);
		
		model.addAttribute("flag", flag);
		
		return flag==true ? "updateSuccess" : "updateFail";
	} //

}