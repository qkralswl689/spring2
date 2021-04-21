package com.javateam.SpringMockTest.service;

import java.util.List;

import com.javateam.SpringMockTest.domain.EmployeesVO;

public interface EmployeesService {
	
	List<EmployeesVO> getEmployeesList();
	EmployeesVO getMember(int employeeId);

}