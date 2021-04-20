package com.javateam.myBatisSample.domain;

import java.util.List;

public interface EmployeesMapper {
	// Mapper : DAO 역할을한다

	 List<Employees> getEmployeesList();
	 Employees getMember(int employeeId);

}
