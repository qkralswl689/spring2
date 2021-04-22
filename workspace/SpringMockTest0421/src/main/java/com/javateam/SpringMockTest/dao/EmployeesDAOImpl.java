package com.javateam.SpringMockTest.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.SpringMockTest.domain.EmployeesVO;
import com.javateam.SpringMockTest.mapper.EmployeesMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EmployeesDAOImpl implements EmployeesDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<EmployeesVO> getEmployeesList() {

		log.info("getEmployeesList");
		return sqlSession.getMapper(EmployeesMapper.class)
						 .getEmployeesList();
	} //

	@Override
	public EmployeesVO getMember(int employeeId) {
		log.info("getMember");
		return sqlSession.getMapper(EmployeesMapper.class)
						 .getMember(employeeId);
	} //

}
