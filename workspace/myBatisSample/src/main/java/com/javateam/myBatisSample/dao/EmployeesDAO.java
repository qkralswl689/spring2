package com.javateam.myBatisSample.dao;

import java.util.List;

import com.javateam.myBatisSample.domain.Employees;

public interface EmployeesDAO {
	
	List<Employees> getEmployeesList();
	Employees getMember(int employeeId);
	
	
	/**
	 * 개별 회원정보 조회
	 * @param employeeId 회원 아이디
	 * @return 회원정보
	 * */
	Employees getMemeberById(int employeeId);
	


}