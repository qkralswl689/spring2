/**
 * 
 */
package com.javateam.SpringMockTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
import org.springframework.http.MediaType;
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
public class MockTest3 {
	
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
		// mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		
		// service = mock(EmployeesServiceImpl.class);
	} //
	
	@Test
	public void mockTest() throws Exception {
		
		log.info("############### mockTest ###############");
		
		int employeeId = 100;
		
		EmployeesVO vo = new EmployeesVO();
		vo.setEmployeeId(employeeId);
		vo.setEmployeeId(100);
		vo.setFirstName("Steven");
		vo.setLastName("King");
		vo.setEmail("SKING");
		vo.setPhoneNumber("515.123.4567");
		vo.setHireDate(Date.valueOf("2003-06-17"));
		vo.setJobId("AD_PRES");
		vo.setSalary(24000);
		vo.setCommissionPct(0.20f);
		vo.setManagerId(100);
		vo.setDepartmentId(90);
		
		// Mockito.doReturn(vo).when(service).getMember(employeeId); // 이 경우는 둘다 가능 		
		when(service.getMember(employeeId)).thenReturn(vo); // 이 경우는 둘다 가능
		
		// controller 맵핑(주소) 대한 개별 단위 테스트
		String result = mockMvc.perform(get("/getOne").param("id", employeeId+"")
										.accept(MediaType.APPLICATION_JSON))
							   .andDo(print())	
							   .andExpect(status().isOk())
							   .andExpect(content().contentType("application/json; charset=UTF-8"))
							   .andReturn()
							   .getResponse()
							   .getContentAsString();
		
		log.info("result : {}", result);
		
		log.info("##############################");
		
		String result2 = mockMvc.perform(get("/getOne").param("id", employeeId+"")
									.accept(MediaType.APPLICATION_JSON))
								.andReturn()
								.getResponse()
								.getContentAsString();
		
		log.info("result2 : {}", result2);
		
		log.info("##############################");
		
		mockMvc.perform(get("/getOne").param("id", employeeId+""))
	 	       .andDo(print())	
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("employeeId").exists())
			   .andExpect(jsonPath("$['lastName']").value("King"));
		
		
		// verify(service, times(0)).getMember(100); // true
		verify(service, times(1)).getMember(100); // false
		// verifyNoMoreInteractions(service);
		
	} //

} //