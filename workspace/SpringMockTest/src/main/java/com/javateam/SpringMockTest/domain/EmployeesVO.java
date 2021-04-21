package com.javateam.SpringMockTest.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeesVO {
	
	 private int employeeId;
	 private String firstName;
     private String lastName;     
     private String email;
     private String phoneNumber;
     private Date hireDate;
     private String jobId;
     private long salary;
     private float commissionPct;
     private int managerId;
     private int departmentId;

}
