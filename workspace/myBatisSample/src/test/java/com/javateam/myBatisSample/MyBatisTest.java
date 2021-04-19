package com.javateam.myBatisSample;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.javateam.myBatisSample.dao.EmployeesDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBatisTest {
	
	@Autowired
	private EmployeesDAO dao;
	
	private int id;

	@Before
	public void setUp() {
		id = 100;
	}

	@Test
	public void test() {
		log.info("vo: " + dao.getMember(id));
		assertEquals(dao.getMember(id).getLastName().equals("king"));
		
	}

}
