package com.javateam.mybatis_project.dao;

import com.javateam.mybatis_project.domain.EmployeesVo;

public interface EmployeesDao {

	/**
	 * 개별 회원정보 조회
	 * @param employeeId 회원 아이디
	 * @return 회원 정보
	 */
	EmployeesVo getEmployeesById(int employeeId);
}
