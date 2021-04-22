package com.javateam.spring.aop.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("msgBean")
public class MsgBeanImpl implements MsgBean {
	
	private static final Logger log
		= LoggerFactory.getLogger(MsgBeanImpl.class);
	
	@Value("Spring !")
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setMsg() {

		log.debug("Hi~! " + name);
	} //

} //