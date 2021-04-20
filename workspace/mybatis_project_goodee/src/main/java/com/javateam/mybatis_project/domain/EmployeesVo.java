package com.javateam.mybatis_project.domain;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class EmployeesVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
