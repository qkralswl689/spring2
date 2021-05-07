package com.javateam.SpringMember;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"classpath*:/spring/root-context.xml",
		"classpath*:/spring/security-context.xml"})
@WebAppConfiguration
@Log4j2
public class PasswordEncoderGenerator {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Test
	public void test() {

		log.info("test");
		int i = 0;
		while (i < 10) {
			String password = "abcd12234";
			
			// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(20);
			String hashedPassword = passwordEncoder.encode(password);
	
			log.info("hashedpassword : "+hashedPassword);
			i++;
		}
	} //

} //
