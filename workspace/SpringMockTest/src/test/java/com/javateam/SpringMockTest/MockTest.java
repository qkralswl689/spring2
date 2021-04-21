/**
 *
 */
package com.javateam.SpringMockTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
// import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

// import org.junit.Assert;
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
public class MockTest {

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
	} //

	@Test
	public void mockTest() throws Exception {

		log.info("############### mockTest ###############");

		List<EmployeesVO> list = new ArrayList<>();
		Mockito.doReturn(list).when(service).getEmployeesList();
		// Assert.assertEquals(list, service.getEmployeesList()); // 이런 경우에는 2번 단위 테스트 한 것이 되어 오류 발생

		// when(service.getEmployeesList()).thenReturn(service.getEmployeesList());
		// 사실상 리턴값(list)이 없으므로(null) 오류 발생

		// controller 맵핑(주소) 대한 개별 단위 테스트
		// mockMvc.perform(get("/getAll")).andExpect(status().isOk());
		
		mockMvc.perform(get("/getAll"))
			  // .andDo(print()) 	
		      .andExpect(forwardedUrl("/WEB-INF/views/employeesList.jsp")) 
			  .andExpect(status().isOk());

		// verify(service).getEmployeesList(); // 현재 상황에서 올바른 사례
		// verify(service.getEmployeesList()); // 현재 상황에서 틀린 사례
		// verifyNoMoreInteractions(service);

	} //
	
} //