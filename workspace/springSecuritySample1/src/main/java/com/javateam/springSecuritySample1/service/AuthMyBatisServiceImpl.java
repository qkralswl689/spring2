/**
 * 
 */
package com.javateam.springSecuritySample1.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javateam.springSecuritySample1.mapper.UserMapper;
import com.javateam.springSecuritySample1.vo.Users;

import lombok.extern.slf4j.Slf4j;

/**
 * @author javateam
 *
 */
@Repository
@Slf4j
public class AuthMyBatisServiceImpl implements AuthMyBatisService {
	
	@Autowired
	private SqlSession sqlSession;

	/**
	 * @see com.javateam.springSecuritySample1.service.AuthMyBatisService#hasUsername(java.lang.String)
	 */
	@Override
	public boolean hasUsername(String username) {

		log.info("hasUsername");
		
		return sqlSession.getMapper(UserMapper.class)
						 .hasUsername(username) == 1 ? true : false;
	} //

	/**
	 * @see com.javateam.springSecuritySample1.service.AuthMyBatisService#insertUsers(com.javateam.springSecuritySample1.vo.Users, java.lang.String)
	 */
	@Override
	public void insertUsers(Users users, String role) {

		log.info("insertUsers");
		
		sqlSession.getMapper(UserMapper.class)
				  .insertUser(users);
		
		sqlSession.getMapper(UserMapper.class)
		  		  .insertUserRoles(users.getUsername(), role);
	} //

} //