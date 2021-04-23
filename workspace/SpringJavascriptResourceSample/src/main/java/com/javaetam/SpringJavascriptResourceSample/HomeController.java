package com.javaetam.SpringJavascriptResourceSample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
	
		log.info("start");
		return "jsSample";
	}
	
}
