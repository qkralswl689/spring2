package com.javateam.myBatisSample.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javateam.myBatisSample.dao.EmployeesDAO;
import com.javateam.myBatisSample.domain.Employees;

@Service
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesDAO dao;

	public List<Employees> getEmployeesList() {

		return dao.getEmployeesList();
	}

	@Override
	public Employees getMember(int employeeId) {
		
		return dao.getMember(employeeId);
	} //

}