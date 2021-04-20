package com.javateam.mybaits_project.dao.impl;

import com.javateam.mybaits_project.domain.EmployeesVo;

public interface EmployeesDao {
	
	/**
	 * 개별 회원정보 조회
	 * @param employeeId 회원아이디
	 * @return 회원정보
	 * */
	
	EmployeesVo getEmployeesById(int employeeId);

}
