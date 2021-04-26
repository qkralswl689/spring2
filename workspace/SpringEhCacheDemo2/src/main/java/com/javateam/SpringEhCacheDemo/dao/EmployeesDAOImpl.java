package com.javateam.SpringEhCacheDemo.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.javateam.SpringEhCacheDemo.domain.EmployeesVO;
import com.javateam.SpringEhCacheDemo.mapper.EmployeesMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EmployeesDAOImpl implements EmployeesDAO {
	
	@Inject
	private SqlSession sqlSession; 
	
	// @Cacheable(value="samplePromotionCache")
	@Override
	public List<EmployeesVO> getAllEmployees() {

		log.info("getAllEmployees");
		
		return sqlSession.getMapper(EmployeesMapper.class).getAllEmployees();
	} //

	// @CacheEvict(value="samplePromotionCache", allEntries=true)
	@Override
	public void updateEmployeesCommissionPct(float commissionPct) {

		log.info("updateEmployeesCommissionPct");
		
		sqlSession.getMapper(EmployeesMapper.class).updateEmployeesCommissionPct(commissionPct);
	} //

} 