package com.javateam.springParamProc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javateam.springParamProc.vo.MemberVO;

/**
 * main action
 */
@Controller
//@RequestMapping("/cmm")
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class); 
	
	//@RequestMapping("/")
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String home() {
		
		//log.info("home !");
		log.debug("home log : debug");
		log.info("home log : info ");
		
		return "home";
	} //
	
	@RequestMapping(value="action", 
					method = {RequestMethod.POST})
	public void actionDemo(Model model,
				       HttpServletRequest request,
				       @RequestParam("name") String name,
				       @RequestParam Map<String, String> map,
				       @RequestBody String str,
				       MemberVO member) throws UnsupportedEncodingException {
		
		log.info("action");
		request.setAttribute("param0",request.getParameter("name")); // 오리지널
		
		model.addAttribute("param1", name); // model객체로 인자 전송, model : dto,vo 
		request.setAttribute("param2", name);				
		model.addAttribute("param3", map.get("name"));
		
		log.info("str :" +str); // System.out.print 와 같은 역할
		
		// @RequestBody는 아래와 같이 URLDecoder.decode()로 한글 변환 처리해야 됨.
		model.addAttribute("param4", URLDecoder.decode(str.split("=")[1], "UTF-8"));
		model.addAttribute("param5", member.getName());
		
	} //
	
	@RequestMapping(value="action2", method=RequestMethod.POST) 
	public ModelAndView action2(@RequestParam("name") String name) {
		
		log.info("ModelAndView action");

		// ModelAndView modelAndView = new ModelAndView("action2");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("action2");

		modelAndView.addObject("param1", name);
		
		return modelAndView;
	} //
	
	@RequestMapping(value = "/formREST", method = RequestMethod.GET)
	public String formREST() {
		
		log.info("formREST !");
		
		return "formREST";
	} //
	
	// since Spring 4.3 
	@GetMapping("/formREST2")
	public String formREST2() {
		
		log.info("formREST2 !");
		
		return "formREST2";
	} //
	
/*	@RequestMapping(value="action3/name/{name}/grade/{grade}", 
					// get,post 방식 전부 사용하려면 아래처럼 입력
					method= {RequestMethod.GET, RequestMethod.POST})
						
	public String action3(@PathVariable("name") String name,
						@PathVariable("grade") int grade,
						Model model) {
		
		log.info("PathVariable action");
		log.info("name : " + name);
		log.info("grade : " + grade);
		
		model.addAttribute("msg", "성공");
		return "action3";*/
	
	@RequestMapping(value="action3/name/{name}/grade/{grade}", 
			// get,post 방식 전부 사용하려면 아래처럼 입력
			method= {RequestMethod.GET, RequestMethod.POST},
			produces="application/json; charset=UTF-8")
	@ResponseBody
		public String action3(@PathVariable("name") String name,
						@PathVariable("grade") int grade,
						Model model) {
		
		log.info("PathVariable action");
		log.info("name : " + name);
		log.info("grade : " + grade);
		
		//model.addAttribute("msg", "성공");
		//return "action3";
		return "{\"msg\" : \"성공\"}";
	} //
	
}
