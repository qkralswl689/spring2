package com.javateam.springFileUploadProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		log.info("Welcome home ! ");
		
		return "redirect:choosePath";
	}

	@RequestMapping(value = "/choosePath", method = RequestMethod.GET)
	public String fileUPload() {

		return "choose_path";
	}
	
	@RequestMapping(value = "/absolutePathForm", method = RequestMethod.GET)
	public String absolutePathForm() {

		return "absolute_file_upload_form";
	}
	
	@RequestMapping(value = "/relativePathForm", method = RequestMethod.GET)
	public String displayForm() {

		return "relative_file_upload_form";
	}
	
}