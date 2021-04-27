package com.javateam.springFileUploadPrj.util;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.springFileUploadProject.util.FileNamingEncoder;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Log
// @Log4j2
public class FileNameEncodingTest {
	
	@Autowired
	@Qualifier("fileNamingEncoder")
	private FileNamingEncoder fileNamingEncoder;
	
	String originalFilename;
	String encodedFilename;
	String decodedFilename;
	
	@Before
	public void setUp() {
		
		// originalFilename = "abcd.pdf";
		// originalFilename = "abcd.bcd_.bcd.pdf";
		originalFilename = "Chrysanthemum.jpg";
	}
	
	@Test
	public void test() {
		
		// log.info("접미어용 난수 : " + fileNamingEncoder.makeRandomNumberPostfix(10));
		
		encodedFilename = fileNamingEncoder.enFilename(originalFilename);
		log.info("인코딩 파일명 : " + encodedFilename);
		
		decodedFilename = fileNamingEncoder.decodeFilename(encodedFilename);
		log.info("원래 파일명 : "+ decodedFilename);
		
		assertEquals(originalFilename, decodedFilename);
		
	} //

}
