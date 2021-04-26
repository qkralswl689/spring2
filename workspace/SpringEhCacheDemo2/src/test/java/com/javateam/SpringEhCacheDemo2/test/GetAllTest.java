package com.javateam.SpringEhCacheDemo2.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.SpringEhCacheDemo.dao.EmployeesDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
      })
@WebAppConfiguration
public class GetAllTest {
	
	private static final Logger log 
		= LoggerFactory.getLogger(GetAllTest.class);
	
	@Autowired
	private EmployeesDAO dao;
	
	@Test
	public void test() {
		
		log.info("단위 테스트");
		assertEquals(107, dao.getAllEmployees().size());
	}

}
