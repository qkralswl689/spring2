package com.javateam.SpringEhCacheDemo.service;

import java.util.List;

import com.javateam.SpringEhCacheDemo.domain.EmployeesVO;

public interface EmployeesService {
	
	List<EmployeesVO> getAll();
	boolean updateCommissionPct(float cp);
} //