/**
 * 
 */
package com.javateam.springIoC.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author javateam
 *
 */
@Component
public class JavaBean2 {
	
	@Value("제어의 역전 프로그래밍")
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}