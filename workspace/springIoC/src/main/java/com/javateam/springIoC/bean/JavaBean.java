/**
 * 
 */
package com.javateam.springIoC.bean;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Java Bean
 * @author javateam
 *
 */
@Component
public class JavaBean {
	
	private static final Logger log 
		= LoggerFactory.getLogger(JavaBean.class); 
	
	public String name;
	
	@Autowired
	public JavaBean2 bean2;

	@Autowired
	public JavaBean(@Value("스프링") String name) {
		
		log.info("생성자 인자 : " + name);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public JavaBean2 getBean2() {
		return bean2;
	}

	public void setBean2(JavaBean2 bean2) {
		this.bean2 = bean2;
	}

	public void print(String arg) {
		
		log.info("JavaBean print");
		log.info("arg : "+arg);
	}

}
