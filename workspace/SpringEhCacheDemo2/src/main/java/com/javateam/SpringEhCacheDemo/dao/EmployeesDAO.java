package com.javateam.SpringEhCacheDemo.dao;

import java.util.List;

import com.javateam.SpringEhCacheDemo.domain.EmployeesVO;

public interface EmployeesDAO {

	 List<EmployeesVO> getAllEmployees();
	 void updateEmployeesCommissionPct(float commissionPct);
}