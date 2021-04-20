package com.javateam.mybatis_project;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.mybatis_project.dao.EmployeesDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
					"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@WebAppConfiguration
public class MyBatisTest {
	
	private static final Logger log 
		= LoggerFactory.getLogger(MyBatisTest.class);
	
	@Autowired
	private EmployeesDao dao;
	
	private int id; 

	@Before
	public void setUp() throws Exception {
		id = 100;
	}

	@Test
	public void test() {
		log.info("getEmployeesById unit test : first_name");
		log.info("emp : " + dao.getEmployeesById(id));
		// assertEquals("Steven", dao.getEmployeesById(id).getFirstName());
	}

}
