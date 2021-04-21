/**
 *
 */
package com.javateam.SpringMockTest.dao;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

// import com.javateam.SpringMockTest.MockTest;

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
public class DAOTest {

	/*
	 * @BeforeClass : 테스트 케이스(테스트 클래스) 내에서 실행 "전" 한 번만 실행됨. 반드시 static 메서드로 지정되어야 함.
	 * @AfterClass : 테스트 케이스(테스트 클래스) 내에서 실행 "후" 한 번만 실행됨. 반드시 static 메서드로 지정되어야 함.
	 * @Before : 테스트 케이스(테스트 클래스) 실행 "전" 반복 실행
	 * @After : 테스트 케이스(테스트 클래스) 실행 "후" 반복 실행
	 * @Test : 실제로 테스트할 메서드에 할당
	 */

	@Autowired
	private EmployeesDAOImpl dao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("##### setUpBeforeClass");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		log.info("###### setUp");
	}

	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		log.info("###### setUpAfterClass");
	}

	/**
	 * @throws Exception
	 */
	@After
	public void setUpAfter() throws Exception {
		log.info("###### setUpAfter");
	}

	/**
	 * Test method for {@link com.javateam.SpringMockTest.dao.EmployeesDAOImpl#getEmployeesList()}.
	 */
	@Test
	public final void testGetEmployeesList() {
		// fail("Not yet implemented");
		log.info("DAO getEmployeesList Unit Test");

		// 테스트 항목 : 회원테이블의 인원수가 107명이 맞는지를 점검
		
		//assertEquals(107, dao.getEmployeesList().size());
		
		// equals와 똑같지만 hamcrest 사용 -> 현업적
		assertThat(dao.getEmployeesList().size(), is(equalTo(107)));

	} //

}