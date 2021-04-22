package com.javateam.SpringMockTest.dao;

import java.util.List;

import com.javateam.SpringMockTest.domain.EmployeesVO;

public interface EmployeesDAO {

	List<EmployeesVO> getEmployeesList();
	EmployeesVO getMember(int employeeId);

}