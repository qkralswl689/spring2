package com.javateam.SpringMockTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javateam.SpringMockTest.dao.EmployeesDAO;
import com.javateam.SpringMockTest.domain.EmployeesVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDAO dao;

	@Override
	public List<EmployeesVO> getEmployeesList() {
		log.info("getEmployeesList");
		return dao.getEmployeesList();
	} //

	@Override
	public EmployeesVO getMember(int employeeId) {
		log.info("getMember");
		return dao.getMember(employeeId);
	} //

} //