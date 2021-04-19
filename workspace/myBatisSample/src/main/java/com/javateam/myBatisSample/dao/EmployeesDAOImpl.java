package com.javateam.myBatisSample.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.myBatisSample.domain.Employees;
import com.javateam.myBatisSample.domain.EmployeesMapper;


@Repository
public class EmployeesDAOImpl implements EmployeesDAO {

	private static final Logger log = 
			(Logger) LoggerFactory.getLogger(EmployeesDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	public List<Employees> getEmployeesList() {

		List<Employees> result = new ArrayList<Employees>();
		EmployeesMapper employeesMapper = sqlSession.getMapper(EmployeesMapper.class);
		result = employeesMapper.getEmployeesList();

		return result;
	}

	@Override
	public Employees getMember(int employeeId) {

		return sqlSession.getMapper(EmployeesMapper.class).getMember(employeeId);

		/*
		 * return sqlSession.selectOne("EmployeesMapper.getMember", employeeId);
		 */
	} //

	@Override
	public Employees getMemeberById(int employeeId) {
		log.info("getMemberById");
		return sqlSession.selectOne("mapper.Employees.getMemberById",employeeId);
	}
}
