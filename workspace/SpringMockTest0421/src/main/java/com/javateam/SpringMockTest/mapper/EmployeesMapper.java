package com.javateam.SpringMockTest.mapper;

import java.util.List;

import com.javateam.SpringMockTest.domain.EmployeesVO;

public interface EmployeesMapper {
	
	List<EmployeesVO> getEmployeesList();
    EmployeesVO getMember(int employeeId);

}