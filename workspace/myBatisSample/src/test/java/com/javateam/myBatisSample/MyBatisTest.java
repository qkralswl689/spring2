package com.javateam.myBatisSample;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.myBatisSample.dao.EmployeesDAO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@WebAppConfiguration
@Slf4j
public class MyBatisTest {
	
	@Autowired
	private EmployeesDAO dao;
	
	private int id;
	
	@Before
	public void setUp() {
		id = 100;
	}
	
	@Test
	public void test() {
		
		log.info("vo : " + dao.getMember(id));
		assertTrue(dao.getMember(id).getLastName().equals("King1"));
	}

}