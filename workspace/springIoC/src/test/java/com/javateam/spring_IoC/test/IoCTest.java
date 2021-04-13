/**
 * 
 */
package com.javateam.spring_IoC.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.springIoC.bean.JavaBean;

import lombok.extern.slf4j.Slf4j;

/**
 * Unit Test
 * @author javateam
 *
 */
// @Slf4j // lombok
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class IoCTest {
	
	private static final Logger log = LoggerFactory.getLogger(IoCTest.class);
	
	// @Inject (결합)
	@Autowired
	private JavaBean bean;

	@Test
	public void test() {
		
		log.info("--------------------------------");
		log.info("unit test");
		
		assertNotNull(bean.bean2.name);
		assertEquals(bean.bean2.name, "제어의 역전 프로그래밍");
		// assertThat(bean.bean2.name, CoreMatchers.is("제어의 역전 프로그래밍"));
		// assertThat(bean.bean2.name, is("제어의 역전 프로그래밍"));
		
		log.info("--------------------------------");
		
	} //
}
