package com.javateam.SpringMockTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javateam.SpringMockTest.service.EmployeesService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	private EmployeesService service;
	
	@RequestMapping("/")
	public String home() {
		
		log.info("home");
		return "redirect:/getAll";
	} //
	
	@RequestMapping("/getAll")
	public String getAll(Model model) {
		
		log.info("getAll");
		model.addAttribute("employeesList", service.getEmployeesList());
		return "employeesList";
	} //
	
	@RequestMapping(value="/getOne", produces="application/json; charset=UTF-8")
	@ResponseStatus(HttpStatus.OK) // http error code "200"(정상 응답 회신)
	@ResponseBody
	public String getOne(@RequestParam("id") int id) throws JsonProcessingException {
		
		log.info("getOne");
		return new ObjectMapper().writeValueAsString(service.getMember(id));
	} //

}