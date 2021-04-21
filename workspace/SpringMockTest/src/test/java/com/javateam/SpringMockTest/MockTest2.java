/**
 * 
 */
package com.javateam.SpringMockTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.javateam.SpringMockTest.controller.HomeController;
import com.javateam.SpringMockTest.domain.EmployeesVO;
import com.javateam.SpringMockTest.service.EmployeesService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author javateam
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
@Slf4j
public class MockTest2 {
	
	@Autowired
	private WebApplicationContext wac;
	
	// 목(모의) 객체(의존성 정보) 삽입
	@Mock
	private EmployeesService service;
	
	// 목(모의) 객체(의존성 정보) 삽입
	@Mock
	private EmployeesVO employeesVO;
	
	// 목 객체에 테스트할 컨트롤러(Controller) 삽입
	@InjectMocks
	private HomeController homeController;
	
	MockMvc mockMvc;
	
	// 단위 테스트 사전 준비 작업
	@Before
	public void setup() {
		
		log.info("before setup");
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	} //
	
	@Test
	public void mockTest() throws Exception {
		
		log.info("############### mockTest ###############");
		
		int employeeId = 100;
		
		EmployeesVO vo = new EmployeesVO();
		
		// Mockito.doReturn(vo).when(service).getMember(emplyeeId); // 이 경우는 둘다 가능 		
		when(service.getMember(employeeId)).thenReturn(vo); // 이 경우는 둘다 가능
		
		// controller 맵핑(주소) 대한 개별 단위 테스트
		mockMvc.perform(get("/getOne").param("id", employeeId+""))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType("application/json;charset=UTF-8"));
		
		verify(service).getMember(100);
		//verifyNoMoreInteractions(service);
		
	} //

} //