package com.javateam.spring.aop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javateam.spring.aop.bean.MsgBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/config/appCtxt.xml")
public class SimpleAOPTest {
	
	private static final Logger log
		= LoggerFactory.getLogger(SimpleAOPTest.class);

	@Autowired
	private MsgBean msgBean;
	
	@Test
	public void testAOP() {
		
		// log level : trace < debug < info < warning < error < fatal
		log.trace("aop test trace");
		log.debug("aop test debug");
		log.info("aop test info");
		log.warn("aop test warning");
		log.error("aop test error");

		msgBean.setMsg();
	} // 

}