package com.javateam.mybatis_project.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.mybatis_project.dao.EmployeesDao;
import com.javateam.mybatis_project.domain.EmployeesVo;

@Repository
public class EmployeesDaoImpl implements EmployeesDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public EmployeesVo getEmployeesById(int employeeId) {
		return sqlSession.selectOne("com.javateam.mybatis_project.mapper.EmployeesMapper.getEmployeesById"
			, employeeId);
	}

}
