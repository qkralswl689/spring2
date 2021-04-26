package com.javateam.SpringEhCacheDemo.mapper;

import java.util.List;

import com.javateam.SpringEhCacheDemo.domain.EmployeesVO;

public interface EmployeesMapper {

	 List<EmployeesVO> getAllEmployees();
	 void updateEmployeesCommissionPct(float commissionPct);

}