package com.javateam.springSecuritySample1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javateam.springSecuritySample1.service.AuthJdbcService;
import com.javateam.springSecuritySample1.service.AuthMyBatisService;
import com.javateam.springSecuritySample1.vo.CustomUser;
import com.javateam.springSecuritySample1.vo.Users;

import lombok.extern.slf4j.Slf4j;
 
@Controller
@Slf4j
public class AuthController {
	
	@Autowired
	private AuthJdbcService authJdbcService;
	
	@Autowired
	private AuthMyBatisService svc;
	
	@RequestMapping("/welcome")
	public void welcome() {
		
		log.info("home");
	}
	
	@RequestMapping("/join")
	public void join() {
		
		log.info("join");
	}
	
	@RequestMapping("/joinAction")
	public String join(@RequestParam("username") String username,
					   @RequestParam("password") String password,
					   Model model) {
		
		log.info("join Action");
		String path = "join";
		
		String hashedPassword = ""; 
		BCryptPasswordEncoder passwordEncoder 
			= new BCryptPasswordEncoder();
		hashedPassword = passwordEncoder.encode(password);

		log.info("hashedPassword : "+hashedPassword);
		
		Users users = new Users(username,
								hashedPassword,
								1);
		
		// authJdbcService.insertUsers(users, "ROLE_USER");
		
		try {
				svc.insertUsers(users, "ROLE_USER");
		} catch (Exception e) {
			log.error("db 에러 발생 : " + e.getMessage());
			model.addAttribute("db_error", e.getMessage());
			path = "/error/db-err";
		} //
		
		return path;
	} // 
	
	
	@RequestMapping(value = "/welcome**", 
					method = RequestMethod.GET)
	public ModelAndView defaultPage() {
	
		log.info("welcome");
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Password Encoder");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;
	} //
    
    @RequestMapping(value="/helloworld", 
    				method = RequestMethod.GET)
    public String helloWorld(ModelMap model) {
    	
    	log.info("hellowWolrd");
    	
	    model.addAttribute("message", "Welcome to the Hello World page");
	    return "helloworld";
    }
    
    @RequestMapping(value= {"/admin/home", "/admin"}, 
					method = RequestMethod.GET)
	public String securedAdminHome(ModelMap model) {
	
    	log.info("/admin/home");
    	
		Object principal = SecurityContextHolder.getContext()
												.getAuthentication()
												.getPrincipal();
		
		CustomUser user = null;
		
		if (principal instanceof CustomUser) {
			user = ((CustomUser)principal);
		}
		
		log.info("user : " +user);
		
		String name = user.getUsername();
		model.addAttribute("username", name);
		model.addAttribute("message", "관리자 페이지에 들어오셨습니다.");
		
		return "/admin/home";
    }
    
    @RequestMapping(value= {"/secured/home", "/secured"}, 
    				method = RequestMethod.GET)
    public String securedHome(ModelMap model) {
    	
    	log.info("/secured/home");
    	
        Object principal = SecurityContextHolder.getContext()
        										.getAuthentication()
        										.getPrincipal();
        
        CustomUser user = null;
        
        if (principal instanceof CustomUser) {
        	user = ((CustomUser)principal);
        }
        
        log.info("user : "+user);
     
	    String name = user.getUsername();
	    model.addAttribute("username", name);
	    model.addAttribute("message", "일반 사용자 페이지에 들어오셨습니다.");
	    
	    return "/secured/home";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
    	
    	log.info("login");
    	
    	return "login";
    }
    
    @RequestMapping(value="/logoutProc", method = RequestMethod.GET)
    public String logout(ModelMap model,
    					 HttpServletRequest request,
    					 HttpServletResponse response) {
    	
    	log.info("logout");
    	
	    Authentication auth 
		    	= SecurityContextHolder.getContext()
		    						   .getAuthentication();
	    
	    log.info("auth : "+auth);
	    
	    // logout !
	    if (auth != null) {    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }

	    return "logout";
    } //
    
    @RequestMapping(value="/loginError", method = RequestMethod.GET)
    // public String loginError(ModelMap model) {
    public String loginError(ModelMap model, HttpSession session) {
    	
	    // model.addAttribute("error", "true");
	    // model.addAttribute("msg", "로그인 정보가 잘못되었습니다.");
	    
		// Spring CustomProvider 인증(Auth) 에러 메시지 처리
		Object secuSess = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
	
		log.info("#### 인증 오류 메시지 : " + secuSess);
		log.info("#### 인증 오류 메시지 : " + secuSess.toString());
	
		model.addAttribute("error", "true");
		// model.addAttribute("msg", "로그인 인증 정보가 잘못되었습니다.");
		model.addAttribute("msg", secuSess);
	
		return "login";
    } //
    
/*   
    @RequestMapping("/access-denied")
    public String accessDenied() {
    	
    	log.info("403");
    	
    	return "redirect:/login";
    }
*/
    
    // id 유무 점검 : spring security bug patch !
 	@RequestMapping("/login/idCheck")
 	public String idCheck(@RequestParam("username") String userName,
 						  Model model) {
 		
 		log.info("userName : "+userName);
 		
 		boolean flag = authJdbcService.hasUsername(userName);
 		System.out.println(flag);
 		model.addAttribute("flag", flag);
 		
 		return "idCheck";
 	} //
 
} //