package com.javateam.springIoC.bean;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class demoVo {

	private String id;
	private String name;
	
	public static void main(String[] args) {
		log.info("demo log 정보");
		new demoVo().setId("abcd");
	}
}
