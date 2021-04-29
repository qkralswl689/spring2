package com.javateam.SpringSessionAttributeDemo;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.javateam.SpringSessionAttributeDemo.domain.DemoVO;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",  
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"  
      })  
@WebAppConfiguration  
@Log4j2  
public class SpringSessionTest {
	
	// 목 세션(mock session) 객체
	protected MockHttpSession session;
	// 목 요청(mock request) 객체
	protected MockHttpServletRequest request;
	
	// 세션 대상 객체
	private DemoVO demoVO;
	
    @Before  
    public void setup() {  

    	log.info("before setup");  
    	
    	demoVO = new DemoVO();
    	demoVO.setId("java");
    	demoVO.setName("홍길동");
    	
    	// 목 세션(mock session) 생성
    	session = new MockHttpSession();
    	session.setAttribute("demoVO", demoVO);
    	
    	request = new MockHttpServletRequest();
    	request.setSession(session);
    	RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    	
    } //  
    
    @After
	public void clear() {
		
		session.clearAttributes();
		session = null;
	}
	
	@Test
	public void test() {
		
		log.info("Mock session Test");
		
		demoVO = (DemoVO)session.getAttribute("demoVO");
		log.info("session id : " + demoVO.getId());
		
		assertEquals(((DemoVO)session.getAttribute("demoVO")).getId(), "java");
		
	} //
	
} //